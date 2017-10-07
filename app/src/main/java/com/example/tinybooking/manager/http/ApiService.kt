package com.example.tinybooking.manager.http

import com.example.tinybooking.dao.AvailableTime
import com.example.tinybooking.dao.ListStoreInfo
import org.joda.time.DateTime
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by schecterza on 27/9/2017 AD.
 */

interface ApiService {

    @GET("/store")
    fun listRepos(): Call<ListStoreInfo>

    @POST("/getbook")
    @FormUrlEncoded
    fun testPost(@Field("sId") storeId: Int,
                 @Field("date") date: String,
                 @Field("numberHours") numberHours: Int,
                 @Field("openTime") openTime: Int,
                 @Field("closeTime") closeTime: Int): Call<AvailableTime>

    @POST("/booking")
    @FormUrlEncoded
    fun bookField(@Field("uId") userId: Int,
                  @Field("sId") fieldId: Int,
                  @Field("date") date: String,
                  @Field("numberHours") numberHours: Int,
                  @Field("time") startItem: Int): Call<String>

}