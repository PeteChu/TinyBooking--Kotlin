package com.example.tinybooking.dao


import com.chibatching.kotpref.KotprefModel

/**
 * Created by schecterza on 9/20/2017.
 */

object UserInfo: KotprefModel() {
    var userDisplayName by stringPref(default = "John Doe")
    var userEmail by stringPref()
    var userId by stringPref(default = "000000")
    var userPhotoUrl by stringPref()

}

