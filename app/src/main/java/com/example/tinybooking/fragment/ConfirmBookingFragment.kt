package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
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

    lateinit var textViewFieldName: TextView
    lateinit var textViewBookDate: TextView
    lateinit var textViewBookTime: TextView

    var fieldId = 0
    lateinit var fieldName: String
    lateinit var bookDate: String
    lateinit var bookTime: String

    var userId: Int = 0
    var numberHours: Int = 0

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
        numberHours = args.getInt("numberHours")

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

        textViewFieldName = rootView.textView_confirm_field_name
        textViewFieldName.text = fieldName

        textViewBookDate = rootView.textView_confirm_date
        textViewBookDate.text = bookDate

        textViewBookTime = rootView.textView_confirm_time
        textViewBookTime.text = bookTime

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

        var call = HttpManager.getInstance().getService().bookField(userId, fieldId, bookDate, numberHours, getTime(bookTime)[0].toInt())
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if (response!!.isSuccessful) {

                    MaterialDialog.Builder(context)
                            .title("จองสำเร็จ")
                            .onPositive(object : MaterialDialog.SingleButtonCallback {
                                override fun onClick(dialog: MaterialDialog, which: DialogAction) {
                                    fragmentManager.popBackStack()
                                }
                            })
                            .positiveText("ตกลง")
                            .show()

                }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {

            }
        })

    }

    fun getTime(time: String): Array<String> {
        var open = time.split("-")[0].split(".")[0]
        var close = time.split("-")[1].split(".")[0]
        return arrayOf(open, close)
    }

    companion object {
        fun newInstance(fieldId: Int, fieldName: String, bookDate: String, bookTime: String, numberHours: Int): ConfirmBookingFragment {
            var fragment = ConfirmBookingFragment()
            var args = Bundle()
            args.putInt("fieldId", fieldId)
            args.putString("fieldName", fieldName)
            args.putString("bookDate", bookDate)
            args.putString("bookTime", bookTime)
            args.putInt("numberHours", numberHours)
            fragment.arguments = args
            return fragment
        }
    }
}