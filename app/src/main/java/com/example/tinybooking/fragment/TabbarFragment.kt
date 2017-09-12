package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tinybooking.R
import kotlinx.android.synthetic.main.fragment_tabbar.view.*

/**
 * Created by schecterza on 9/12/2017 AD.
 */

class TabbarFragment: Fragment() {

    lateinit var btnPage4: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_tabbar, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {
        btnPage4 = rootView.page4
        btnPage4.setOnClickListener(myOnclick)
    }

    var myOnclick = View.OnClickListener { v ->
        when(v.id) {
            R.id.page4 -> goToProfileView()
        }
    }

    fun goToProfileView() {
        fragmentManager.beginTransaction()
                .replace(R.id.content_main_activity, ProfileFragment.newInstance())
                .addToBackStack(null)
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