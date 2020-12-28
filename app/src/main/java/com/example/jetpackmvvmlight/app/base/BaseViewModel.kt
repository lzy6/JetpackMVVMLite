package com.example.jetpackmvvmlight.app.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackmvvmlight.apiExceptionCode
import com.example.jetpackmvvmlight.app.Constant
import com.example.jetpackmvvmlight.app.service.CommonService
import com.example.jetpackmvvmlight.entity.Page
import com.example.jetpackmvvmlight.errorToast
import com.lyl.chaoji.app.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    /**
     * 接口service
     */
    protected val mApi: CommonService by lazy {
        return@lazy RetrofitServiceManager.instance.create(CommonService::class.java)
    }

    /**
     * 网络请求(普通)
     * @param before 请求之前
     * @param response 请求结果
     * @param error 错误结果
     * @param complete 接口请求后(成功/失败)执行
     * @param retry 错误重试，设置最大次数，可自定义设置条件或无条件
     */
    fun <T> launchUI(
        before: (() -> Unit)? = null,
        response: suspend CoroutineScope.() -> T,
        error: ((e: Exception, code: Int) -> Unit)? = null,
        complete: (() -> Unit)? = null,
        retry: ((e: Exception, code: Int) -> Unit)? = null,
        liveData: StateLiveData<T>
    ) =
        viewModelScope.launch {
            try {
                before?.invoke()
                liveData.setBefore(Constant.BEFORE)
                liveData.value = response()
            } catch (e: Exception) {
                errorToast(e)
                if (e is ApiException) {
                    error?.invoke(e, apiExceptionCode(e))
                    retry?.invoke(e, apiExceptionCode(e))
                    liveData.setError(Constant.ERROR, e.code)
                } else {
                    error?.invoke(e, -1)
                    retry?.invoke(e, -1)
                }
            }
        }.invokeOnCompletion {
            complete?.invoke()
            liveData.setComplete(Constant.COMPLETE)
        }

    /**
     * 网络请求(分页)
     * @param before 请求之前
     * @param response 请求结果
     * @param error 错误结果
     * @param complete 接口请求后(成功/失败)执行
     * @param retry 错误重试，设置最大次数，可自定义设置条件或无条件
     */
    fun <T> launchUIPage(
        page: Int,
        before: (() -> Unit)? = null,
        response: suspend CoroutineScope.() -> Page<T>,
        error: ((e: Exception, code: Int) -> Unit)? = null,
        complete: (() -> Unit)? = null,
        retry: ((e: Exception, code: Int) -> Unit)? = null,
        liveData: StateLiveData<Page<T>>
    ) =
        viewModelScope.launch {
            try {
                before?.invoke()
                liveData.setBefore(Constant.BEFORE)
                liveData.value = response()
                if (page == 1 && liveData.value!!.total < 1) {
                    liveData.setStateLayout(
                        Constant.STATE_LAYOUT,
                        Constant.VIEW_EMPTY
                    )
                }
                liveData.setHasMore(Constant.HAS_MORE, liveData.value!!)
            } catch (e: Exception) {
                errorToast(e)
                if (e is ApiException) {
                    error?.invoke(e, apiExceptionCode(e))
                    retry?.invoke(e, apiExceptionCode(e))
                    liveData.setError(Constant.ERROR, e.code)
                } else {
                    error?.invoke(e, -1)
                    retry?.invoke(e, -1)
                }
            }
        }.invokeOnCompletion {
            complete?.invoke()
            liveData.setComplete(Constant.COMPLETE)
        }


}



