package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.ActivityCompat
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
import com.example.tinybooking.R
import com.example.tinybooking.adapter.BookItemList
import com.example.tinybooking.adapter.FavoriteItemListAdapter
import kotlinx.android.synthetic.main.fragment_book.view.*
import kotlinx.android.synthetic.main.fragment_book_store.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * Created by schecterza on 9/12/2017 AD.
 */

class BookFragment : Fragment() {
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
        var activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(rootView.book_fragment_toolbar)
        var actionBar = activity.supportActionBar!!
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.setTitle("Book")

        mRecyclerView = rootView.list_book
        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager

        mAdapter = BookItemList()
        mRecyclerView!!.adapter = mAdapter

        mSnapHelper = LinearSnapHelper()
        mSnapHelper!!.attachToRecyclerView(mRecyclerView)
    }

    companion object {
        fun newInstance(): BookFragment {
            var fragment = BookFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}