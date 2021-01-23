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
import kotlinx.coroutines.async
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
        error: ((e: Throwable, code: Int) -> Unit)? = null,
        complete: (() -> Unit)? = null,
        retry: ((e: Throwable, code: Int) -> Unit)? = null,
        liveData: StateLiveData<T>
    ) =
        viewModelScope.launch {
            before?.invoke()
            liveData.setBefore(Constant.BEFORE)
            runCatching {
                response()
            }.onSuccess {
                liveData.value = it
            }.onFailure {
                errorToast(it)
                if (it is ApiException) {
                    error?.invoke(it, apiExceptionCode(it))
                    retry?.invoke(it, apiExceptionCode(it))
                    liveData.setError(Constant.ERROR, it.code)
                } else {
                    error?.invoke(it, -1)
                    retry?.invoke(it, -1)
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
        error: ((e: Throwable, code: Int) -> Unit)? = null,
        complete: (() -> Unit)? = null,
        retry: ((e: Throwable, code: Int) -> Unit)? = null,
        liveData: StateLiveData<Page<T>>
    ) =
        viewModelScope.launch {
            before?.invoke()
            liveData.setBefore(Constant.BEFORE)
            runCatching {
                response()
            }.onSuccess {
                liveData.value = it
                if (page == 1 && it.total < 1) {
                    liveData.setStateLayout(
                        Constant.STATE_LAYOUT,
                        Constant.VIEW_EMPTY
                    )
                }
                liveData.setHasMore(Constant.HAS_MORE, it)
            }.onFailure {
                errorToast(it)
                if (it is ApiException) {
                    error?.invoke(it, apiExceptionCode(it))
                    retry?.invoke(it, apiExceptionCode(it))
                    liveData.setError(Constant.ERROR, it.code)
                } else {
                    error?.invoke(it, -1)
                    retry?.invoke(it, -1)
                }
            }
        }.invokeOnCompletion {
            complete?.invoke()
            liveData.setComplete(Constant.COMPLETE)
        }

    /**
     * 网络请求(列表)
     * @param before 请求之前
     * @param response 请求结果
     * @param error 错误结果
     * @param complete 接口请求后(成功/失败)执行
     * @param retry 错误重试，设置最大次数，可自定义设置条件或无条件
     * @param cache 读取缓存
     */
    fun <T> launchUIPageCache(
        before: (() -> Unit)? = null,
        cache: (suspend CoroutineScope.() -> ArrayList<T>)? = null,
        response: suspend CoroutineScope.() -> ArrayList<T>,
        error: ((e: Throwable, code: Int) -> Unit)? = null,
        complete: (() -> Unit)? = null,
        retry: ((e: Throwable, code: Int) -> Unit)? = null,
        liveData: StateLiveData<ArrayList<T>>
    ) {
        //如果缓存数据比网络数据慢，则不加载缓存数据
        var isRequestSuccess = false

        viewModelScope.launch {
            cache?.let {
                async {
                    it().apply {
                        if (!isRequestSuccess) {
                            liveData.value = this
                        }
                    }
                }
            }

            runCatching {
                response()
            }.onSuccess {
                before?.invoke()
                liveData.value = it
                isRequestSuccess = true
                CacheManager.getInstance().saveCache(Constant.CACHE, it)
            }.onFailure {
                errorToast(it)
                if (it is ApiException) {
                    error?.invoke(it, apiExceptionCode(it))
                    retry?.invoke(it, apiExceptionCode(it))
                    liveData.setError(Constant.ERROR, it.code)
                } else {
                    error?.invoke(it, -1)
                    retry?.invoke(it, -1)
                }
            }
        }.invokeOnCompletion {
            complete?.invoke()
            liveData.setComplete(Constant.COMPLETE)
        }
    }

}



