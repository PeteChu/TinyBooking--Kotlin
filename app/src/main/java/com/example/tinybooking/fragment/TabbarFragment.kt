package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.tinybooking.R
import kotlinx.android.synthetic.main.fragment_tabbar.view.*

/**
 * Created by schecterza on 9/12/2017 AD.
 */

class TabbarFragment: Fragment() {

    lateinit var btnPage1: LinearLayout
    lateinit var btnPage2: LinearLayout
    lateinit var btnPage3: LinearLayout
    lateinit var btnPage4: LinearLayout

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_tabbar, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {
        btnPage1 = rootView.page1
        btnPage2 = rootView.page2
        btnPage3 = rootView.page3
        btnPage4 = rootView.page4

        btnPage1.setOnClickListener(myOnclick)
        btnPage2.setOnClickListener(myOnclick)
        btnPage3.setOnClickListener(myOnclick)
        btnPage4.setOnClickListener(myOnclick)
    }

    var myOnclick = View.OnClickListener { v ->
        when(v.id) {
            R.id.page1 -> goToMainView()
            R.id.page2 -> goToBookView()
            R.id.page3 -> goToNotificationView()
            R.id.page4 -> goToProfileView()
        }
    }

    fun goToMainView() {
        fragmentManager.beginTransaction()
                .replace(R.id.content_main_activity, MainFragment.newInstance())
                .commit()

    }

    fun goToBookView() {
        fragmentManager.beginTransaction()
                .replace(R.id.content_main_activity, MyBookingFragment.newInstance())
                .commit()
    }

    fun goToNotificationView() {
        fragmentManager.beginTransaction()
                .replace(R.id.content_main_activity, NotificationFragment.newInstance())
                .commit()
    }

    fun goToProfileView() {
        fragmentManager.beginTransaction()
                .replace(R.id.content_main_activity, SettingFragment.newInstance())
                .commit()
    }

    companion object {
        fun newInstance(): TabbarFragment {
            var fragment = TabbarFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}