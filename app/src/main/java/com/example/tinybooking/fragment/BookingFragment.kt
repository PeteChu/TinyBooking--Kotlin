package com.example.tinybooking.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.airbnb.android.airmapview.AirMapView
import com.airbnb.android.airmapview.listeners.OnCameraChangeListener
import com.airbnb.android.airmapview.listeners.OnCameraMoveListener
import com.airbnb.android.airmapview.listeners.OnMapClickListener
import com.airbnb.android.airmapview.listeners.OnMapInitializedListener
import com.example.tinybooking.R
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks
import com.github.ksoichiro.android.observablescrollview.ScrollState
import com.github.ksoichiro.android.observablescrollview.ScrollUtils
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_book_store.view.*

/**
 * Created by schecterza on 28/9/2017 AD.
 */

class BookingFragment: Fragment(), ObservableScrollViewCallbacks,
        OnMapClickListener, OnMapInitializedListener,
        OnCameraChangeListener, OnCameraMoveListener {

    lateinit var mToolbarView: Toolbar
    lateinit var mImageView: ImageView
    lateinit var mScrollView: ObservableScrollView
    lateinit var mMapView: AirMapView
    var mParallaxImageHeight: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_book_store, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        var activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(rootView.toolbar)
        var actionBar = activity.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)

        mImageView = rootView.image

        mToolbarView = rootView.toolbar
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0f, resources.getColor(R.color.primary)))

        mToolbarView.setNavigationOnClickListener {
            activity.onBackPressed()
        }

        mScrollView = rootView.scroll
        mScrollView.setScrollViewCallbacks(this)
        mParallaxImageHeight = resources.getDimensionPixelSize(R.dimen.parallax_image_height)

        mMapView = rootView.map_view
        mMapView.setOnMapClickListener(this)
        mMapView.setOnCameraChangeListener(this)
        mMapView.setOnCameraMoveListener(this)
        mMapView.setOnMapInitializedListener(this)
        mMapView.initialize(childFragmentManager)





    }

    override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        onScrollChanged(mScrollView.scrollY, false, false);
    }

    override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
        val baseColor = resources.getColor(R.color.primary)
        val alpha = Math.min(1.toFloat(), (scrollY / mParallaxImageHeight).toFloat())
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor))
    }

    override fun onDownMotionEvent() {

    }

    override fun onMapInitialized() {
        val airbnbLatLng = LatLng(37.771883, -122.405224)
        mMapView.animateCenterZoom(airbnbLatLng, 15)
        mMapView.drawCircle(airbnbLatLng, 50, Color.parseColor("#79CCCD"),5, Color.parseColor("#8079CCCD"))
        mMapView.setMyLocationEnabled(false)
    }

    override fun onMapClick(latLng: LatLng?) {

    }

    override fun onCameraChanged(latLng: LatLng?, zoom: Int) {

    }

    override fun onCameraMove() {

    }

    companion object {
        fun newInstance(): BookingFragment {
            var fragment = BookingFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}