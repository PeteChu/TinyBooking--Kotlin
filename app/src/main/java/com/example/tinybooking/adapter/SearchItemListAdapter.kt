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
import kotlinx.android.synthetic.main.item_search.view.*

/**
 * Created by schecterza on 27/9/2017 AD.
 */

class SearchItemListAdapter(context: Context, listStoreInfo: ListStoreInfo) : RecyclerView.Adapter<SearchItemListAdapter.ViewHolder>() {

    var mListStoreInfo = listStoreInfo.store
    var mContext = context

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindData(mContext, mListStoreInfo, position)

    }

    override fun getItemCount(): Int = mListStoreInfo.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(context: Context, data: List<StoreInfo>, position: Int) {
            var fieldData = data[position]
            Picasso.with(context).load(fieldData.image).into(itemView.imageView_field_thumbnail)
            itemView.textView_search_field_name.text = fieldData.name
            itemView.textView_search_field_desc.text = fieldData.address
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