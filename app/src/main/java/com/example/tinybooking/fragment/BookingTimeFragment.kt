package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.R
import kotlinx.android.synthetic.main.fragment_booking_time.view.*

/**
 * Created by schecterza on 6/10/2017 AD.
 */

class BookingTimeFragment : Fragment() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    lateinit var mAdapter: RecyclerView.Adapter<*>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_booking_time, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        mRecyclerView = rootView.list_booking_time
        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = mLayoutManager


    }

    companion object {
        fun newInstance(): BookingTimeFragment {
            var fragment = BookingTimeFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}