package com.example.tinybooking.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import com.example.tinybooking.R
import kotlinx.android.synthetic.main.item_notification.view.*

/**
 * Created by weiweieuro on 22/9/2560.
 */
class ListNoti @JvmOverloads constructor(
        context : Context,
        attrs : AttributeSet? = null ,
        defStyleattr: Int = 0
):FrameLayout(context,attrs,defStyleattr){
    lateinit var item : LinearLayout

    init {
        initInflate()
        this.item = item_noti
        item.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"click",Toast.LENGTH_SHORT).show()
        })
    }


    fun initInflate(){
        LayoutInflater.from(context).inflate(R.layout.item_notification,this,true)
    }


}