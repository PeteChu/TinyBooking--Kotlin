package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.R
import com.example.tinybooking.adapter.NotificationListAdapter
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.fragment_notification.view.*

/**
 * Created by schecterza on 9/12/2017 AD.
 */

class NotificationFragment : Fragment() {
    lateinit var list : RecyclerView
    var adapterNoti : NotificationListAdapter = NotificationListAdapter()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_notification, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        list = rootView.noti_list
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapterNoti
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