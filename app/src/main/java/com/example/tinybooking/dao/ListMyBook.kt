package com.example.tinybooking.dao

/**
 * Created by schecterza on 7/10/2017 AD.
 */

data class ListMyBook(
        val book: List<Book>
)

data class Book(
        val bid: Int,
        val uid: Int,
        val sid: Int,
        val dateandtime: String,
        val hour: Int,
        val confirm: Boolean
)