package com.example.tinybooking.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tinybooking.ContentActivity
import com.example.tinybooking.R

/**
 * Created by schecterza on 27/9/2017 AD.
 */

class SearchItemListAdapter : RecyclerView.Adapter<SearchItemListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_notification, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindData()

    }

    override fun getItemCount(): Int = 5

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindData() {
            itemView.setOnClickListener { onClick(itemView.context) }

        }

        private fun onClick(c: Context) {
            var intent = Intent(c, ContentActivity::class.java)
            c.startActivity(intent)
        }


    }

}