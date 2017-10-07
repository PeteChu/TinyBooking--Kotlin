package com.example.tinybooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tinybooking.fragment.NotificationDetail

class NotidetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notidetail)
        initInstant()
    }
    fun initInstant(){
        var intent = getIntent().extras
        supportFragmentManager.beginTransaction()
                .add(R.id.area_notification_detail, NotificationDetail.newInstance(intent))
                .commit()
    }
}
