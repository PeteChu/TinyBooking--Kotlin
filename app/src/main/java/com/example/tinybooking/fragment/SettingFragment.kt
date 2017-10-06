package com.example.tinybooking.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.chibatching.kotpref.Kotpref
import com.example.tinybooking.ProfileActivity
import com.example.tinybooking.R
import com.example.tinybooking.SignInActivity
import com.example.tinybooking.dao.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_setting.view.*


/**
 * Created by schecterza on 9/12/2017 AD.
 */

class SettingFragment : Fragment() {

    lateinit var btnLogIn: LinearLayout
    lateinit var btnLogOut: LinearLayout

    lateinit var textViewUserName: TextView
    lateinit var imageViewUserProfile: ImageView
    lateinit var tvEditprofile : TextView


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_setting, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {
        (activity as AppCompatActivity).supportActionBar!!.hide()

        Kotpref.init(context)

        btnLogIn = rootView.btn_log_in
        btnLogOut = rootView.btn_log_out
        btnLogIn.setOnClickListener(myOnClick)
        btnLogOut.setOnClickListener(myOnClick)

        textViewUserName = rootView.textView_user_name
        imageViewUserProfile = rootView.image_user_profile
        tvEditprofile = rootView.btn_editprofile
        tvEditprofile.setOnClickListener(View.OnClickListener {
            var intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
        })

        var user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            updateUser()
        } else {

        }


    }

    val myOnClick = View.OnClickListener{v ->
        when(v) {
            v.btn_log_in -> goToSignInView()
//            v.btn_log_out -> signOut()
        }
    }

    private fun goToSignInView() {
        var intent = Intent(context, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun signOut() {
        SignInFragment.getInstance().signOut()
        UserInfo.clear()
    }

    private fun updateUser() {

        val userData = UserInfo
        textViewUserName.text = userData.userDisplayName
        Picasso.with(context).load(userData.userPhotoUrl).into(imageViewUserProfile)
    }


    companion object {
        fun newInstance(): SettingFragment {
            var fragment = SettingFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}