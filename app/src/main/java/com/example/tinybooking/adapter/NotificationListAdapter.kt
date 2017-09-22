package com.example.tinybooking.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tinybooking.R
import kotlinx.android.synthetic.main.item_notification.view.*


/**
 * Created by weiweieuro on 22/9/2560.
 */
class NotificationListAdapter() : RecyclerView.Adapter<NotificationListAdapter.ViewHolder>() {

    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent!!.context).inflate(R.layout.item_notification, parent, false)
        var viewHolder = ViewHolder(v)
        mContext = parent.context
        v.setOnClickListener(View.OnClickListener {

        })
        return viewHolder
    }



    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bind(mContext,position)
    }

    override fun getItemCount(): Int {
        return 5
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card = itemView.item_noti
        fun bind(mContext:Context,position: Int){
            card.setOnClickListener(View.OnClickListener {
                Toast.makeText(mContext,"click $position",Toast.LENGTH_SHORT).show()
            })
        }


    }


}
