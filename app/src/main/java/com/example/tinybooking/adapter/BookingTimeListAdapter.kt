package com.example.tinybooking.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by schecterza on 6/10/2017 AD.
 */

class BookingTimeListAdapter: RecyclerView.Adapter<BookingTimeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData() {

        }
    }
}