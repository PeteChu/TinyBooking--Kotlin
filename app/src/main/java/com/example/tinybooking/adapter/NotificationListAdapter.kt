package com.example.tinybooking.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tinybooking.NotidetailActivity
import com.example.tinybooking.R
import com.squareup.picasso.Picasso
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
        return 3
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card = itemView.item_noti
        val imageItem = itemView.noti_item_image
        val title = itemView.noti_title
        val subtitle = itemView.noti_subtitle
        lateinit var urlImage : String
        lateinit var tmpTitle : String
        fun bind(mContext:Context,position: Int){

            if(position==0){
                 urlImage = "https://www.mx7.com/i/0ee/RYTz3Z.jpg"
                tmpTitle = "Limited offering for members only!"
                subtitle.text = "On September 25"
            }else if(position==1){
                 urlImage = "https://www.mx7.com/i/296/WqUmpb.jpg"
                tmpTitle = "Dao Stadium Get 20% off any time."
                subtitle.text = "20% OFF"
            }else{
                urlImage = "https://www.mx7.com/i/1be/CePQzr.jpg"
                tmpTitle = "Welcome to our new stadium!"
                subtitle.text = "We’re now ready for serving you at Gaysorn Tower"
            }
            Picasso.with(mContext).load(urlImage).into(imageItem)
            title.text = tmpTitle
            card.setOnClickListener(View.OnClickListener {
                var intent = Intent(mContext, NotidetailActivity::class.java)
                intent.putExtra("url",urlImage)
                intent.putExtra("title",tmpTitle)
                mContext.startActivity(intent)
            })
        }


    }


}
