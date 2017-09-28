package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.R

/**
 * Created by schecterza on 28/9/2017 AD.
 */

class BookingFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_book_store, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

    }

    companion object {
        fun newInstance(): BookingFragment {
            var fragment = BookingFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}