package com.example.commonlib.app

import com.example.commonlib.entity.BaseResponse
import com.google.gson.Gson
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