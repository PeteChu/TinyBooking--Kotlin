package com.example.tinybooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tinybooking.fragment.BookingFragment

class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        initInstances()
    }

    fun initInstances() {

        supportFragmentManager.beginTransaction()
                .add(R.id.content_container_content_activity, BookingFragment.newInstance())
                .commit()

    }

}
