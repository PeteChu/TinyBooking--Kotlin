package com.example.tinybooking.dao

/**
 * Created by schecterza on 27/9/2017 AD.
 */

data class ListStoreInfo(
        val store: List<StoreInfo>
)

data class StoreInfo(
        val sid: Int,
        val name: String,
        val phone: String,
        val email: String,
        val address: String,
        val field: Int,
        val opentime: String,
        val image: String
)
