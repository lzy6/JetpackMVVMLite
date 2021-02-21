package com.example.jetpackmvvmlight.service

import com.example.commonlib.entity.BaseResponse
import com.example.commonlib.entity.Page
import com.example.jetpackmvvmlight.entity.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APPService {

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

    /**
     * 缓存列表
     */
    @FormUrlEncoded
    @POST(".../pageEntity")
    suspend fun cacheEntity(): BaseResponse<ArrayList<CacheEntity>>

}