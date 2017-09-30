package com.example.tinybooking.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.tinybooking.EditActivity
import com.example.tinybooking.R
import kotlinx.android.synthetic.main.fragment_viewprofile.view.*


class ViewProfileFragment :Fragment(){
        lateinit var btnEdit:LinearLayout
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater!!.inflate(R.layout.fragment_viewprofile, container, false)
        initInstances(rootview)
        return rootview
    }

    fun initInstances(rootview : View){
        btnEdit = rootview.btn_edit_profile
        btnEdit.setOnClickListener(View.OnClickListener {
            var intent = Intent(context, EditActivity::class.java)
            startActivity(intent)
        })
    }
    companion object {
        fun newInstance(): ViewProfileFragment {
            var fragment = ViewProfileFragment()
            var args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
}