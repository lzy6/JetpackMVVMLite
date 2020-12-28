package com.example.jetpackmvvmlight.app.base

import com.example.jetpackmvvmlight.entity.Page
import com.example.jetpackmvvmlight.entity.State
import com.example.jetpackmvvmlight.ui.widget.unpeeklivedata.UnPeekLiveData

class StateLiveData<T> : UnPeekLiveData<T>() {

    val state = UnPeekLiveData<State>()

    private var stateEntity = State()


    /**
     * 允许后台返回null
     */
    fun allowNull(allowNullValue: Boolean): StateLiveData<T> {
        this.isAllowNullValue = allowNullValue
        return this
    }

    /**
     * 接口出错
     */
    fun setError(code: Int, curErrorCode: Int) {
        state.value = stateEntity.apply {
            stateCode = code
            errorCode = curErrorCode
        }
    }

    /**
     * 接口请求前
     */
    fun setBefore(code: Int) {
        state.value = stateEntity.apply {
            stateCode = code
        }
    }

    /**
     * 是否完成接口请求
     */
    fun setComplete(code: Int) {
        state.value = stateEntity.apply {
            stateCode = code
        }
    }

    /**
     * 是否加载更多
     */
    fun setHasMore(code: Int, curPage: Page<*>) {
        state.value = stateEntity.apply {
            stateCode = code
            page = curPage
        }
    }

    fun setHasMore(code: Int) {
        state.value = stateEntity.apply {
            stateCode = code
        }
    }

    fun setStateLayout(code: Int, curStateLayout: Int) {
        state.value = stateEntity.apply {
            stateCode = code
            stateLayout = curStateLayout
        }
    }
}
