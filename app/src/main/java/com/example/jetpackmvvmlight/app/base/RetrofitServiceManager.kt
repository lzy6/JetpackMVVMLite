package com.example.jetpackmvvmlight.app.base

import com.example.jetpackmvvmlight.*
import com.example.jetpackmvvmlight.app.Constant
import com.example.jetpackmvvmlight.app.HandleErrorInterceptor
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitServiceManager {
    private val okHttpClient: OkHttpClient
    private val retrofit: Retrofit

    init {

        val gson = GsonBuilder().registerTypeAdapter(String::class.java, StringAdapter())
            .registerTypeAdapter(Boolean::class.java, BooleanAdapter())
            .registerTypeAdapter(Integer::class.java, IntegerDefault0Adapter()).create()

        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_CONNECT_TIME.toLong(), TimeUnit.SECONDS)//连接超时时间
            .writeTimeout(DEFAULT_WRITE_TIME.toLong(), TimeUnit.SECONDS)//设置写操作超时时间
            .readTimeout(DEFAULT_READ_TIME.toLong(), TimeUnit.SECONDS)//设置读操作超时时间
            .addInterceptor(getRequestHeader())//设置请求头
            .addInterceptor(HandleErrorInterceptor())//设置日志拦截
            .build()



        retrofit = Retrofit.Builder()
            .client(okHttpClient)//设置使用okhttp网络请求
            .baseUrl(REQUEST_PATH)//设置服务器路径
            .addConverterFactory(GsonConverterFactory.create(gson))//添加转化库，默认是Gson
            .build()

    }

    /**
     * 设置请求头
     */
    private fun getRequestHeader(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val modifiedUrl = originalRequest.url().newBuilder().build()
            val request = originalRequest.newBuilder()
            request.addHeader("version-code", BuildConfig.VERSION_CODE.toString())
            val token = mmkv().decodeString(Constant.TOKEN)
            if (token.isNotNull()) {
                request.addHeader("token", token)
            }
            val build = request.url(modifiedUrl).build()
            chain.proceed(build)
        }
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    companion object {
        private const val DEFAULT_CONNECT_TIME = 10
        private const val DEFAULT_WRITE_TIME = 30
        private const val DEFAULT_READ_TIME = 30
        private const val REQUEST_PATH = BuildConfig.BASE_URL

        private object SingletonHolder {
            internal val INSTANCE = RetrofitServiceManager()
        }

        /*
    * 获取RetrofitServiceManager
    **/
        val instance: RetrofitServiceManager
            get() = SingletonHolder.INSTANCE
    }
}

