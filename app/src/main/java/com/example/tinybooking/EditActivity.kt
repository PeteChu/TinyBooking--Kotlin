package com.example.tinybooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tinybooking.fragment.EditProfileFragment

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        initInstant()
    }

    fun initInstant(){
        supportFragmentManager.beginTransaction()
                .add(R.id.area_edit, EditProfileFragment.newInstance())
                .commit()
    }

}
