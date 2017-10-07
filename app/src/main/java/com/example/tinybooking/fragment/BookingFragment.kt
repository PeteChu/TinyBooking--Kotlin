package com.example.tinybooking.fragment

import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.airbnb.android.airmapview.AirMapView
import com.airbnb.android.airmapview.listeners.*
import com.example.tinybooking.R
import com.example.tinybooking.dao.AvailableTime
import com.example.tinybooking.manager.HttpManager
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks
import com.github.ksoichiro.android.observablescrollview.ScrollState
import com.github.ksoichiro.android.observablescrollview.ScrollUtils
import com.google.android.gms.maps.model.LatLng
import com.shawnlin.numberpicker.NumberPicker
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_book_store.view.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by schecterza on 28/9/2017 AD.
 */

class BookingFragment : Fragment(), ObservableScrollViewCallbacks, OnMapClickListener,
        OnMapInitializedListener, DatePickerListener {

    lateinit var mToolbarView: Toolbar
    lateinit var mImageView: ImageView
    lateinit var mScrollView: ObservableScrollView
    lateinit var mMapView: AirMapView
    lateinit var mDatePicker: HorizontalPicker
    lateinit var mHourPicker: NumberPicker
    lateinit var btnCheckAvailable: TextView
    var mParallaxImageHeight: Int = 0

    lateinit var pickedDate: DateTime
    var numberHours: Int = 0

    var fieldId: Int = 0
    lateinit var fieldName: String
    lateinit var fieldOpenTime: String
    lateinit var fieldImage: String
    lateinit var fieldTel: String
    lateinit var fieldEmail: String
    lateinit var fieldAddress: String

    lateinit var imageViewField: ImageView
    lateinit var textViewFieldName: TextView
    lateinit var textViewOpenTime: TextView
    lateinit var textViewTel: TextView
    lateinit var textViewEmail: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_book_store, container, false)
        initInstances(rootView)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        var fieldData = arguments.get("fieldData") as Bundle
        fieldId = fieldData.get("fieldId") as Int
        fieldName = fieldData.get("fieldName") as String
        fieldOpenTime = fieldData.get("fieldOpenTime") as String
        fieldImage = fieldData.get("fieldImage") as String
        fieldTel = fieldData.get("fieldTel") as String
        fieldEmail = fieldData.get("fieldEmail") as String
        fieldAddress = fieldData.get("fieldAddress") as String

        super.onCreate(savedInstanceState)
    }

    fun initInstances(rootView: View) {

        var activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(rootView.toolbar)
        var actionBar = activity.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)

//        Initialize Toolbar
        mToolbarView = rootView.toolbar
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0f, resources.getColor(R.color.primary)))

        mToolbarView.setNavigationOnClickListener {
            activity.onBackPressed()
        }

//        Initialize ScrollView
        mScrollView = rootView.scroll
        mScrollView.setScrollViewCallbacks(this)
        mParallaxImageHeight = resources.getDimensionPixelSize(R.dimen.parallax_image_height)

//        Initialize MapView
        mMapView = rootView.map_view
        mMapView.setOnMapClickListener(this)
        mMapView.setOnMapInitializedListener(this)
        mMapView.initialize(childFragmentManager)

//        Initialize Date Picker
        mDatePicker = rootView.datePicker
        mDatePicker.setListener(this)
                .showTodayButton(true)
                .init()

        mDatePicker.backgroundColor = Color.WHITE
        mDatePicker.setDate(DateTime.now())

//        Initialize Hour Picker
        mHourPicker = rootView.hour_picker
        numberHours = mHourPicker.value
        mHourPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            Toast.makeText(context, newVal.toString(), Toast.LENGTH_SHORT).show()
            numberHours = newVal
        }

//        Initialize Button Check Available
        btnCheckAvailable = rootView.btn_check_available
        btnCheckAvailable.setOnClickListener(myOnClick)

//        Initialize
        imageViewField = rootView.imageView_field
        Picasso.with(context).load(fieldImage).into(imageViewField)

        textViewFieldName = rootView.textView_field_name
        textViewFieldName.text = fieldName

        textViewOpenTime = rootView.textView_open_time
        textViewOpenTime.text = fieldOpenTime

        textViewTel = rootView.textView_tel
        textViewTel.text = fieldTel

        textViewEmail = rootView.textView_email
        textViewEmail.text = fieldEmail

    }

    var myOnClick = View.OnClickListener { v ->
        when (v) {
            btnCheckAvailable -> {
                checkAvailableTime()
            }
        }
    }

    private fun checkAvailableTime() {

        var call = HttpManager.getInstance().getService().testPost(3, pickedDate.toString("Y-MM-d"), numberHours, 10, 24)
        call.enqueue(object : Callback<AvailableTime> {
            override fun onResponse(call: Call<AvailableTime>?, response: Response<AvailableTime>?) {
                if (response!!.isSuccessful) {
                    var listAvailableTime = response.body()!!.availableTime

                    if (listAvailableTime.isNotEmpty()) {
                        MaterialDialog.Builder(context)
                                .title("ช่วงเวลาที่ว่าง")
                                .items(listAvailableTime)
                                .itemsCallbackSingleChoice(-1, object : MaterialDialog.ListCallbackSingleChoice {
                                    override fun onSelection(dialog: MaterialDialog?, itemView: View?, which: Int, text: CharSequence?): Boolean {
                                        fragmentManager.beginTransaction()
                                                .replace(R.id.content_container_content_activity, ConfirmBookingFragment.newInstance(text.toString()))
                                                .addToBackStack(null)
                                                .commit()
                                        return true
                                    }
                                })
                                .positiveText("ตกลง")
                                .negativeText("ยกเลิก")
                                .show()
                    } else {
                        MaterialDialog.Builder(context)
                                .title("ช่วงเวลาที่ว่าง")
                                .content("ขออภัย ไม่มีช่วงเวลาที่ว่างในวันเวลาที่คุณต้องการ")
                                .positiveText("ตกลง")
                                .show()
                    }


                }
            }

            override fun onFailure(call: Call<AvailableTime>?, t: Throwable?) {

            }
        })
    }

    override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        onScrollChanged(mScrollView.scrollY, false, false)
    }

    override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
        val baseColor = resources.getColor(R.color.colorPrimary)
        val alpha = Math.min(1.toFloat(), (scrollY / mParallaxImageHeight).toFloat())
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor))
    }

    override fun onDownMotionEvent() {

    }


    override fun onMapInitialized() {
        val currentLocation = LatLng(37.771883, -122.405224)
        mMapView.animateCenterZoom(currentLocation, 15)
        mMapView.drawCircle(currentLocation, 75, Color.parseColor("#79CCCD"), 5, Color.parseColor("#8079CCCD"))
        mMapView.setMyLocationEnabled(false)
    }

    override fun onMapClick(latLng: LatLng?) {

        fragmentManager.beginTransaction()
                .replace(R.id.content_container_content_activity, ShowMapFragment.newInstance(latLng!!))
                .addToBackStack(null)
                .commit()

    }


    override fun onDateSelected(dateSelected: DateTime?) {
        Toast.makeText(context, dateSelected!!.toString(), Toast.LENGTH_SHORT).show()
        pickedDate = dateSelected
    }
    fun getTime(time:String): Array<String> {
        var open = time.split("-")[0].split(".")[0]
        var close = time.split("-")[1].split(".")[0]
        return arrayOf(open,close);

    }

    companion object {
        fun newInstance(fieldData: Bundle): BookingFragment {
            var fragment = BookingFragment()
            var args = Bundle()
            args.putBundle("fieldData", fieldData)
            fragment.arguments = args
            return fragment
        }
    }
}