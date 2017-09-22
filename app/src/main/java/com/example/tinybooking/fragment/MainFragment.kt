package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.R
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.view.Gravity
import android.widget.LinearLayout
import com.example.tinybooking.adapter.FavoriteItemListAdapter
import kotlinx.android.synthetic.main.fragment_main.view.*


/**
 * Created by schecterza on 9/12/2017 AD.
 */

class MainFragment : Fragment() {

    private var mSnapHelper: SnapHelper? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        mRecyclerView = rootView.list_favorite_item
        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager

        mAdapter = FavoriteItemListAdapter()
        mRecyclerView!!.adapter = mAdapter

        mSnapHelper = LinearSnapHelper()
        mSnapHelper!!.attachToRecyclerView(mRecyclerView)


    }

    companion object {
        fun newInstance(): MainFragment {
            var fragment = MainFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}