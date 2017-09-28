package com.example.tinybooking.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.R

/**
 * Created by schecterza on 27/9/2017 AD.
 */

class SearchItemListAdapter : RecyclerView.Adapter<SearchItemListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int = 5

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindData() {

        }
    }
}