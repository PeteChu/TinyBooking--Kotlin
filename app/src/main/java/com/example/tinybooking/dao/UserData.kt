package com.example.tinybooking.dao

import android.net.Uri
import com.google.firebase.auth.FirebaseUser

/**
 * Created by schecterza on 9/20/2017.
 */

class UserData(var userDisplayName: String = "John Doe" ,
                    var userEmail: String = "xxx@mail.com",
                    var userId: String = "1234",
                    var userPhotoUrl: Uri = Uri.parse("www.google.com")) {

    companion object {
        fun setUserData(userData: FirebaseUser): UserData {
            var data = UserData()
            data.userDisplayName = userData.displayName!!
            data.userEmail = userData.email!!
            data.userId = userData.uid
            data.userPhotoUrl = userData.photoUrl!!

            return data
        }

        fun getUserData(): UserData {
            var data = UserData()
            return data
        }
    }
}

