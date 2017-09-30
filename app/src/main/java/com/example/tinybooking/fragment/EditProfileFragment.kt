package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.tinybooking.R

class EditProfileFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater!!.inflate(R.layout.fragment_editprofile, container, false)
        initInstances(rootview)
        return rootview
    }

    fun initInstances(rootview : View){

    }
    companion object {
        fun newInstance(): EditProfileFragment {
            var fragment = EditProfileFragment()
            var args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
}