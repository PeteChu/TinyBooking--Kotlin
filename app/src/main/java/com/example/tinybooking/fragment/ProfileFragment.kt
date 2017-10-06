package com.example.tinybooking.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.tinybooking.R
import com.example.tinybooking.dao.UserInfo
import kotlinx.android.synthetic.main.fragment_viewprofile.view.*
import android.widget.Toast
import com.example.tinybooking.EditActivity
import com.example.tinybooking.manager.PostService


class ProfileFragment : Fragment() {

    lateinit var btnEdit: LinearLayout

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_viewprofile, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        var toolbar = rootView.profile_fragment_toolbar
        var activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(toolbar)
        var actionBar = activity.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }


        btnEdit = rootView.btn_edit_profile
        btnEdit.setOnClickListener {

            var intent = Intent(context, EditActivity::class.java)
            startActivity(intent)
        }

        var userInfo = UserInfo

        rootView.profile_username.text = userInfo.userDisplayName
        rootView.profile_email.text = userInfo.userEmail

    }

    companion object {
        fun newInstance(): ProfileFragment {
            var fragment = ProfileFragment()
            var args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
    public fun thread(start: Boolean = true, isDaemon: Boolean = false, contextClassLoader: ClassLoader? = null, name: String? = null, priority: Int = -1, block: () -> Unit): Thread {
        val thread = object : Thread() {
            public override fun run() {
               Toast.makeText(context,"123",Toast.LENGTH_SHORT).show()
            }
        }
        if (isDaemon)
            thread.isDaemon = true
        if (priority > 0)
            thread.priority = priority
        if (name != null)
            thread.name = name
        if (contextClassLoader != null)
            thread.contextClassLoader = contextClassLoader
        if (start)
            thread.start()
        return thread
    }
}


