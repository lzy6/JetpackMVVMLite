package com.example.jetpackmvvmlight

import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.reflect.Type
import kotlin.jvm.Throws


class StringAdapter : TypeAdapter<String>() {
    override fun write(writer: JsonWriter?, value: String?) {
        if (value == null) {
            writer?.nullValue();

            return;
        }
        writer?.value(value);
    }

    override fun read(reader: JsonReader): String {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return ""
        }
        return reader.nextString()
    }
}

class BooleanAdapter : TypeAdapter<Boolean>() {
    override fun write(writer: JsonWriter?, value: Boolean?) {
        if (value == null) {
            writer?.nullValue()
            return
        }
        writer?.value(value)
    }

    override fun read(reader: JsonReader): Boolean {
        val peek = reader.peek()
        if (peek == JsonToken.NULL) {
            //服务器返回null 按false处理
            reader.nextNull()
            return false
        } else if (peek == JsonToken.NUMBER) {
            //服务器返回number类型1 按true处理
            return reader.nextInt() == 1
        } else if (peek == JsonToken.STRING) {
            //服务器返回string类型 1或者true 按true处理
            return reader.nextString() in setOf("1", "true")
        }
        return reader.nextBoolean()
    }
}

class IntegerDefault0Adapter : JsonSerializer<Int>, JsonDeserializer<Int> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Int? {
        try {
            if (json.asString == "" || json.asString == "null") {
                //定义为int类型,如果后台返回""或者null,则返回0
                return 0
            }
        } catch (ignore: Exception) {
        }

        try {
            return json.asInt
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }

    }

    override fun serialize(src: Int?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src!!)
    }
}
