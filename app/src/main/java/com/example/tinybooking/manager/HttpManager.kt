package com.example.tinybooking.manager

import com.example.tinybooking.manager.http.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by schecterza on 27/9/2017 AD.
 */

class HttpManager {

    var apiService: ApiService

    constructor() {

        var retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.165:9999/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofit.create(ApiService::class.java)

    }


    fun getService(): ApiService {
        return apiService
    }

    companion object {
        fun getInstance(): HttpManager {
            var instance = HttpManager()
            return instance
        }
    }
}