package com.example.tinybooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar

import com.example.tinybooking.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    lateinit var toolbar : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initInstant()
    }
    fun initInstant(){
        toolbar = editprofile_toolbar
        toolbar.setNavigationOnClickListener{

            onBackPressed()
        }
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Profile")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        supportFragmentManager.beginTransaction()
                .add(R.id.area_profile, ProfileFragment.newInstance())
                .commit()
    }
}
