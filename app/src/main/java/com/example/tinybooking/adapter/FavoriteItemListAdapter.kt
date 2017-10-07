package com.example.tinybooking.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.ContentActivity
import com.example.tinybooking.R
import com.example.tinybooking.dao.ListStoreInfo
import com.example.tinybooking.dao.StoreInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_favorite.view.*

/**
 * Created by schecterza on 9/22/2017.
 */

class FavoriteItemListAdapter(context: Context, listStoreInfo: ListStoreInfo) : RecyclerView.Adapter<FavoriteItemListAdapter.ViewHolder>() {

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
            Picasso.with(context).load(fieldData.image).into(itemView.cv_favorite_imageView)
            itemView.cv_favorite_field_name.text = fieldData.name
            itemView.cv_favorite_field_time.text = fieldData.opentime
            itemView.cv_favorite_field_tel.text = fieldData.phone
            itemView.cv_favorite_field_addr.text = fieldData.address
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