package com.example.tinybooking.manager.http

import com.example.tinybooking.dao.ListStoreInfo
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by schecterza on 27/9/2017 AD.
 */

interface ApiService {

    @GET("store")
    fun listRepos() : Call<ListStoreInfo>

}