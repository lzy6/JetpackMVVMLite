package com.example.jetpackmvvmlight.app.service

import com.example.jetpackmvvmlight.entity.BaseResponse
import com.example.jetpackmvvmlight.entity.Page
import com.example.jetpackmvvmlight.entity.PageUser
import com.example.jetpackmvvmlight.entity.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CommonService {

    /**
     * 可以返回null
     */
    @POST(".../mayNull")
    suspend fun mayNull(): BaseResponse<Any?>

    /**
     * 返回单个实体
     */
    @POST(".../singEntity")
    suspend fun singEntity(): BaseResponse<User>

    /**
     * 分页
     */
    @FormUrlEncoded
    @POST(".../pageEntity")
    suspend fun pageEntity(@Field("pageNum") pageNum: Int): BaseResponse<Page<PageUser>>

}