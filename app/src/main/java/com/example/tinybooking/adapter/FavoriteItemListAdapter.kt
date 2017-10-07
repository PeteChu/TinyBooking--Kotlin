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
import com.example.tinybooking.dao.ListStoreInfo
import com.example.tinybooking.dao.StoreInfo

/**
 * Created by schecterza on 9/22/2017.
 */

class FavoriteItemListAdapter(context: Context, listStoreInfo: ListStoreInfo): RecyclerView.Adapter<FavoriteItemListAdapter.ViewHolder>() {

    var mListStoreInfo = listStoreInfo.store
    var mContext = context

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.cardview_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindData(mContext, mListStoreInfo, position)
    }

    override fun getItemCount(): Int = 2

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(context: Context, data: List<StoreInfo>, position: Int) {
            var fieldData = data[position]
            itemView.setOnClickListener { onClick(itemView.context, fieldData) }
        }

        private fun onClick(c: Context, fieldData: StoreInfo) {
            var intent = Intent(c, ContentActivity::class.java)
            intent.putExtra("fieldId", fieldData.sid)
            intent.putExtra("fieldName", fieldData.name)
            intent.putExtra("fieldOpenTime", fieldData.opentime)
            intent.putExtra("fieldImage", fieldData.image)
            intent.putExtra("fieldTel", fieldData.phone)
            intent.putExtra("fieldEmail", fieldData.email)
            intent.putExtra("fieldAddress", fieldData.address)
            c.startActivity(intent)
        }
    }
}