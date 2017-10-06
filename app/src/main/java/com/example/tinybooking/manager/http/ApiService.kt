package com.example.tinybooking.manager.http

import com.example.tinybooking.dao.ListStoreInfo
import org.joda.time.DateTime
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by schecterza on 27/9/2017 AD.
 */

interface ApiService {

    @GET("store")
    fun listRepos() : Call<ListStoreInfo>

    @POST("test")
    fun testPost(@Body dateTime: DateTime) : Call<String>

}