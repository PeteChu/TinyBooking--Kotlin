package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.R


class SetProfileFragment:Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater!!.inflate(R.layout.fragment_settingprofile, container, false)
        initInstances(rootview)
        return rootview
    }

    fun initInstances(rootview : View){

    }
    companion object {
        fun newInstance(): SetProfileFragment {
            var fragment = SetProfileFragment()
            var args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
}