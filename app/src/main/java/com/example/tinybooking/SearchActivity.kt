package com.example.tinybooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tinybooking.fragment.SearchFragment

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initInstances()
    }

    fun initInstances() {

        supportFragmentManager.beginTransaction()
                .add(R.id.content_search_fragment, SearchFragment.newInstance())
                .commit()
    }
}
