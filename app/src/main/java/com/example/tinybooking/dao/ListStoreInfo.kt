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



//{
//
//    @SerializedName("sid")
//    @Expose
//    private val sid: Int? = null
//    @SerializedName("name")
//    @Expose
//    private val name: String? = null
//    @SerializedName("phone")
//    @Expose
//    private val phone: String? = null
//    @SerializedName("email")
//    @Expose
//    private val email: String? = null
//    @SerializedName("address")
//    @Expose
//    private val address: String? = null
//    @SerializedName("field")
//    @Expose
//    private val field: Int? = null
//    @SerializedName("opentime")
//    @Expose
//    private val opentime: String? = null
//    @SerializedName("image")
//    @Expose
//    private val image: String? = null
//
//}