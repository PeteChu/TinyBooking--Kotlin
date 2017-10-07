package com.example.tinybooking.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.R
import com.example.tinybooking.adapter.NotificationListAdapter
import kotlinx.android.synthetic.main.fragment_book.view.*
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.fragment_notification.view.*

/**
 * Created by schecterza on 9/12/2017 AD.
 */

class NotificationFragment : Fragment() {
    lateinit var rf : SwipeRefreshLayout
    lateinit var list : RecyclerView
    var adapterNoti : NotificationListAdapter = NotificationListAdapter()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_notification, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        var activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(rootView.noti_fragment_toolbar)
        var actionBar = activity.supportActionBar!!
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.setTitle("Massage")
        rf = rootView.nofi_refresh
        list = rootView.noti_list
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapterNoti

        rf.setColorSchemeResources(R.color.colorPrimary)
        rf.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            val handler = Handler()
            handler.postDelayed(Runnable {
                rf.isRefreshing = false
            }, 3000)
        })
    }

    companion object {
        fun newInstance(): NotificationFragment {
            var fragment = NotificationFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}