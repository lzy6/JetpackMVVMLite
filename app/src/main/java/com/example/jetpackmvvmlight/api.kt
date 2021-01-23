package com.example.jetpackmvvmlight

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackmvvmlight.app.Constant
import com.example.jetpackmvvmlight.app.base.BaseViewModel
import com.example.jetpackmvvmlight.entity.Page
import com.example.jetpackmvvmlight.entity.State
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.lyl.chaoji.app.ApiException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @param state 状态类
 * @param before 接口请求前
 * @param complete 接口请求后(成功/失败)执行
 * @param hasMore 是否有下一页，做分页
 * @param stateLayout 状态页面
 */
fun apiState(
    state: State,
    before: (() -> Unit)? = null,
    complete: (() -> Unit)? = null,
    error: ((code: Int) -> Unit)? = null,
    hasMore: ((page: Page<*>) -> Unit)? = null,
    stateLayout: ((layout: Int) -> Unit)? = null
) {
    when (state.stateCode) {
        Constant.BEFORE -> before?.invoke()
        Constant.COMPLETE -> complete?.invoke()
        Constant.ERROR -> error?.invoke(state.errorCode)
        Constant.HAS_MORE -> hasMore?.invoke(state.page)
        Constant.STATE_LAYOUT -> stateLayout?.invoke(state.stateLayout)
    }
}

fun errorToast(t: Throwable) {
    val msg: String? = if (t is UnknownHostException || t is ConnectException) {
        "网络不可用"
    } else if (t is SocketTimeoutException) {
        "请求网络超时"
    } else if (t is HttpException) {
        convertStatusCode(t)
    } else if (t is JsonParseException || t is ParseException || t is JSONException || t is JsonIOException) {
        "数据解析错误"
    } else if (t is ApiException) {
        apiExceptionMsg(t)
    } else {
        "请求失败"
    }
    if (!msg.isNullOrEmpty()) toast(msg)

}

private fun convertStatusCode(httpException: HttpException): String {
    return when (httpException.code()) {
        500 -> "服务器发生错误"
        404 -> "请求地址不存在"
        else -> httpException.message()
    }
}

private fun apiExceptionMsg(apiException: ApiException): String {
    return when (apiException.code) {
        500 -> "服务器发生错误"
        404 -> "请求地址不存在"
        else -> if (!apiException.info.isNullorEmpty()) apiException.info!! else ""
    }
}

fun apiExceptionCode(apiException: ApiException): Int {
    if (apiException.code == 0 && apiException.info.isNull()) {
        return -1
    }
    return apiException.code
}

inline fun <reified T : BaseViewModel> viewModels(activity: FragmentActivity): T =
    ViewModelProvider(activity).get(T::class.java)