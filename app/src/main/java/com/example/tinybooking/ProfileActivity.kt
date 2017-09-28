package com.example.tinybooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View

import com.example.tinybooking.R
import com.example.tinybooking.fragment.SetProfileFragment
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

        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Setting")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)




        supportFragmentManager.beginTransaction()
                .add(R.id.area_profile,SetProfileFragment.newInstance())
                .commit()
    }
}
