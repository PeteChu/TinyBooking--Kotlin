package com.example.tinybooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tinybooking.fragment.MainFragment
import com.example.tinybooking.fragment.TabbarFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInstances()
    }

    private fun initInstances() {

        supportFragmentManager.beginTransaction()
                .add(R.id.content_main_activity, MainFragment.newInstance())
                .commit()

        supportFragmentManager.beginTransaction()
                .add(R.id.tabbar_main_activity, TabbarFragment.newInstance())
                .commit()

    }
}
