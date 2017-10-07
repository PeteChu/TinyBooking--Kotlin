package com.example.tinybooking.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tinybooking.R
import com.example.tinybooking.dao.Book
import com.example.tinybooking.dao.ListStoreInfo
import com.example.tinybooking.dao.StoreInfo
import com.example.tinybooking.manager.HttpManager
import com.squareup.picasso.Picasso
import retrofit2.Callback
import kotlinx.android.synthetic.main.cardview_book.view.*
import retrofit2.Call
import retrofit2.Response

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

    override fun getItemCount(): Int = mListMyBook.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fieldImg = "http://innovationintelligence.com/wp-content/uploads/2017/07/players-shutterstock_604002233.jpg"

        fun binData(c: Context, listMyBook: List<Book>, position: Int) {
            var myBookData = listMyBook[position]
            findField(c, myBookData)
        }

        fun seperateDateTime(dateAndTime: String): Array<String> {
            var date = dateAndTime.split(" ")[0]
            var time = dateAndTime.split(" ")[1]
            return arrayOf(date, time)
        }

        fun findField(context: Context, fieldBook: Book) {

            var fieldData: StoreInfo
            var call = HttpManager.getInstance().getService().findField(fieldBook.sid)
            call.enqueue(object : Callback<ListStoreInfo> {
                override fun onResponse(call: Call<ListStoreInfo>?, response: Response<ListStoreInfo>?) {
                    if (response!!.isSuccessful) {
                        fieldData = response.body()!!.store[0]
                        Picasso.with(context).load(fieldData.image).into(itemView.imageView_myBook_field)
                        itemView.textView_myBook_field_name.text = fieldData.name
                        itemView.textView_myBook_book_date.text = seperateDateTime(fieldBook.dateandtime)[0]
                        itemView.textView_myBook_book_time.text = seperateDateTime(fieldBook.dateandtime)[1]

                    }
                }

                override fun onFailure(call: Call<ListStoreInfo>?, t: Throwable?) {

                }
            })

        }

    }

}