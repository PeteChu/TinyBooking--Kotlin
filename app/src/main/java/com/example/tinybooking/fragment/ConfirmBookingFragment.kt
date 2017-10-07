package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.chibatching.kotpref.Kotpref
import com.example.tinybooking.R
import com.example.tinybooking.dao.UserInfo
import com.example.tinybooking.manager.HttpManager
import kotlinx.android.synthetic.main.fragment_confirm_booking.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by schecterza on 6/10/2017 AD.
 */

class ConfirmBookingFragment : Fragment() {

    lateinit var btnBack: LinearLayout
    lateinit var btnSubmit: LinearLayout

    var fieldId = 0
    lateinit var fieldName: String
    lateinit var bookDate: String
    lateinit var bookTime: String

    var userId: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_confirm_booking, container, false)
        initInstances(rootView)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        var args = arguments
        fieldId = args.getInt("fieldId")
        fieldName = args.getString("fieldName")
        bookDate = args.getString("bookDate")
        bookTime = args.getString("bookTime")

        super.onCreate(savedInstanceState)
    }

    fun initInstances(rootView: View) {

        Kotpref.init(context)

        userId = UserInfo.userTinyId


//        Initialize toolbar
        var toolbar = rootView.confirm_fragment_toolbar
        var activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(toolbar)
        var actionBar = activity.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.title = "ยืนยันการจอง"

        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }

//        Initialize submit button
        btnSubmit = rootView.btn_submit_confirm_fragment
        btnSubmit.setOnClickListener(myOnClick)


//        Initialize back button
        btnBack = rootView.btn_back_confirm_fragment
        btnBack.setOnClickListener(myOnClick)


    }

    var myOnClick = View.OnClickListener { v ->
        when (v.id) {
            R.id.btn_submit_confirm_fragment -> {
                bookField()
            }
            R.id.btn_back_confirm_fragment -> {
                fragmentManager.popBackStack()
            }
        }
    }

    fun bookField() {

        Toast.makeText(context, "Book", Toast.LENGTH_SHORT).show()
        var call = HttpManager.getInstance().getService().bookField(userId, fieldId, bookDate, 2, 19)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if (response!!.isSuccessful) {
                    Toast.makeText(context, response.body()!!.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {

            }
        })

    }

    companion object {
        fun newInstance(fieldId: Int, fieldName: String, bookDate: String, bookTime: String): ConfirmBookingFragment {
            var fragment = ConfirmBookingFragment()
            var args = Bundle()
            args.putInt("fieldId", fieldId)
            args.putString("fieldName", fieldName)
            args.putString("bookDate", bookDate)
            args.putString("bookTime", bookTime)
            fragment.arguments = args
            return fragment
        }
    }
}