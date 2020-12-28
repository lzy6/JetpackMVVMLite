package com.example.jetpackmvvmlight.app

import android.util.Log
import com.example.jetpackmvvmlight.entity.BaseResponse
import com.google.gson.Gson
import com.lyl.chaoji.app.ApiException
import okhttp3.Response

class HandleErrorInterceptor : ResponseBodyInterceptor() {

    override fun intercept(response: Response, url: String?, body: String?): Response {
        var baseResponse: BaseResponse<*>? = null
        try {
            baseResponse = Gson().fromJson(body, BaseResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (baseResponse != null && !baseResponse.isSuccess()) {
            throw ApiException(baseResponse.status, baseResponse.msg)
        }
        try {
            baseResponse = Gson().fromJson(body, BaseResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}