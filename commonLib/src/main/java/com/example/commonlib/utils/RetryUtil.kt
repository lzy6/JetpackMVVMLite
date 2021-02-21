package com.example.commonlib.utils


class RetryUtil() {

    //当前重试次数
    private var mRetryNum = 0

    //最大重试次数
    private var mMaxRetryNum = 0

    /**
     * 构造方法
     * @param maxRetryNum 最大重试次数(数次+本身次数)
     */
    constructor(maxRetryNum: Int) : this() {
        mMaxRetryNum = maxRetryNum
    }

    /**
     * 无条件重试
     */
    fun retry(request: () -> Unit) {
        if (mMaxRetryNum <= mRetryNum) {
            return
        }
        mRetryNum++
        request.invoke()
    }

    /**
     * 根据exceptionType(异常类型)进行重试
     * @param exceptionType 异常类型
     */
    fun retryToException(exceptionType: Boolean, request: () -> Unit) {
        if (mMaxRetryNum <= mRetryNum || !exceptionType) {
            return
        }
        mRetryNum++
        request.invoke()
    }

    /**
     * 根据code类型进行重试
     * @param codeType code类型
     */
    fun retryToCode(codeType: Boolean, request: () -> Unit) {
        if (mMaxRetryNum <= mRetryNum || !codeType) {
            return
        }
        mRetryNum++
        request.invoke()
    }

}