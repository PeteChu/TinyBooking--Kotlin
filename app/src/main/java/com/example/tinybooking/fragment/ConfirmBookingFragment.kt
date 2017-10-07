package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.R
import kotlinx.android.synthetic.main.fragment_confirm_booking.view.*

/**
 * Created by schecterza on 6/10/2017 AD.
 */

class ConfirmBookingFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_confirm_booking, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        var toolbar = rootView.confirm_fragment_toolbar
        var activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(toolbar)
        var actionBar = activity.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.title = "ยืนยันการจอง"

        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }


    }

    companion object {
        fun newInstance(): ConfirmBookingFragment {
            var fragment = ConfirmBookingFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}