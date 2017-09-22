package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.tinybooking.R
import kotlinx.android.synthetic.main.fragment_profile.view.*


/**
 * Created by schecterza on 9/12/2017 AD.
 */

class ProfileFragment: Fragment() {
        lateinit var btnLangague : LinearLayout
        lateinit var btnHelp: LinearLayout
        lateinit var btnFeedback : LinearLayout
        lateinit var btnShare : LinearLayout
        lateinit var swicthNoti : Switch
        lateinit var tvLangague : TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_profile, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {
        btnLangague = rootView.set_langague
        btnHelp = rootView.set_help
        btnFeedback = rootView.set_feedback
        btnShare = rootView.set_share
        swicthNoti = rootView.switch_noti
        tvLangague = rootView.langague

        btnLangague.setOnClickListener(View.OnClickListener {
           tvLangague.text = if(tvLangague.text.equals("English")) "ไทย" else "English"
        })
        btnHelp.setOnClickListener(View.OnClickListener {
            Toast.makeText(rootView.context, "Help", Toast.LENGTH_SHORT).show()
        })
        btnFeedback.setOnClickListener(View.OnClickListener {
            Toast.makeText(rootView.context, "Feedback", Toast.LENGTH_SHORT).show()
        })
        btnShare.setOnClickListener(View.OnClickListener {
            Toast.makeText(rootView.context, "share", Toast.LENGTH_SHORT).show()
        })
        swicthNoti.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
            compoundButton, b -> Toast.makeText(rootView.context, "$b", Toast.LENGTH_SHORT).show()

        })
    }

    companion object {
        fun newInstance(): ProfileFragment {
            var fragment = ProfileFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}


