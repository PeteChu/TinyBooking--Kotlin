package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.tinybooking.R
import com.example.tinybooking.dao.UserData
import kotlinx.android.synthetic.main.fragment_profile.view.*

/**
 * Created by schecterza on 9/12/2017 AD.
 */

class ProfileFragment: Fragment() {

    lateinit var btnLogin: LinearLayout

    lateinit var textViewUserName: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_profile, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        btnLogin = rootView.btn_log_in
        btnLogin.setOnClickListener(myOnClick)

        textViewUserName = rootView.textView_user_name

        val userData = UserData.getUserData()
        Toast.makeText(activity, userData.userDisplayName, Toast.LENGTH_LONG).show()

    }

    val myOnClick = View.OnClickListener{v ->
        when(v) {
            v -> goToSignInView()
        }
    }

    fun goToSignInView() {
        fragmentManager.beginTransaction()
                .replace(R.id.content_main_activity, SignInFragment.newInstance())
                .addToBackStack(null)
                .commit()
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


