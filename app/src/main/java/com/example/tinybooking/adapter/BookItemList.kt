package com.example.tinybooking.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.R
import com.example.tinybooking.dao.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_book.view.*

/**
 * Created by schecterza on 9/22/2017.
 */

class BookItemList(context: Context, listMyBook: List<Book>) : RecyclerView.Adapter<BookItemList.ViewHolder>() {

    var mListMyBook = listMyBook
    var mContext = context


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.cardview_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.binData(mContext, mListMyBook, position)
    }

    override fun getItemCount(): Int = 5

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fieldImg = "http://innovationintelligence.com/wp-content/uploads/2017/07/players-shutterstock_604002233.jpg"

        fun binData(c: Context, listMyBook: List<Book>, position: Int) {
            var myBookData = listMyBook[position]
            Picasso.with(c).load(fieldImg).into(itemView.imageView_myBook_field)

            itemView.setOnClickListener { onClick(itemView.context) }
        }

        fun onClick(c: Context) {

        }
    }
}