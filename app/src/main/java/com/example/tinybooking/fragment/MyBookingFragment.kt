package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.chibatching.kotpref.Kotpref
import com.example.tinybooking.R
import com.example.tinybooking.adapter.BookItemList
import com.example.tinybooking.dao.ListMyBook
import com.example.tinybooking.dao.UserInfo
import com.example.tinybooking.manager.HttpManager
import kotlinx.android.synthetic.main.fragment_book.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by schecterza on 9/12/2017 AD.
 */

class MyBookingFragment : Fragment() {
    private var mSnapHelper: SnapHelper? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_book, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        Kotpref.init(context)

        var activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(rootView.book_fragment_toolbar)
        var actionBar = activity.supportActionBar!!
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.setTitle("My Booking")

        mRecyclerView = rootView.list_book
        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager

        mSnapHelper = LinearSnapHelper()
        mSnapHelper!!.attachToRecyclerView(mRecyclerView)

        loadData()
    }

    fun loadData() {
        var call = HttpManager.getInstance().getService().myBook(UserInfo.userTinyId)
        call.enqueue(object : Callback<ListMyBook> {
            override fun onResponse(call: Call<ListMyBook>?, response: Response<ListMyBook>?) {
                if (response!!.isSuccessful) {
                    var listMyBook = response.body()!!.book

                    if (listMyBook.isNotEmpty()) {
                        mAdapter = BookItemList(context, listMyBook)
                        mRecyclerView!!.adapter = mAdapter
                    }
                }

            }

            override fun onFailure(call: Call<ListMyBook>?, t: Throwable?) {
            }
        })
    }

    companion object {
        fun newInstance(): MyBookingFragment {
            var fragment = MyBookingFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}