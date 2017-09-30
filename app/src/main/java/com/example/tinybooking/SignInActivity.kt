package com.example.tinybooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tinybooking.fragment.SignInFragment

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initInstances()
    }

    fun initInstances() {

        supportFragmentManager.beginTransaction()
                .add(R.id.sign_in_container, SignInFragment.newInstance())
                .commit()

    }

}
