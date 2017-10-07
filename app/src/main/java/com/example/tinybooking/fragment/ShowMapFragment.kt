package com.example.tinybooking.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.airbnb.android.airmapview.AirMapView
import com.airbnb.android.airmapview.listeners.OnMapInitializedListener
import com.example.tinybooking.R
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_map.view.*

/**
 * Created by schecterza on 7/10/2017 AD.
 */

class ShowMapFragment : Fragment(), OnMapInitializedListener {

    lateinit var mMapView: AirMapView
    var mLat: Double = 0.0
    var mLng: Double = 0.0


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_map, container, false)
        initInstances(rootView)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mLat = arguments.getDouble("lat")
        mLng = arguments.getDouble("lng")

        super.onCreate(savedInstanceState)
    }

    fun initInstances(rootView: View) {

        //        Initialize MapView
        mMapView = rootView.show_map_view
        mMapView.setOnMapInitializedListener(this)
        mMapView.initialize(childFragmentManager)

    }

    override fun onMapInitialized() {
        var currentLocation = LatLng(mLat, mLng)
        mMapView.animateCenterZoom(currentLocation, 15)
        mMapView.drawCircle(currentLocation, 75, Color.parseColor("#79CCCD"), 5, Color.parseColor("#8079CCCD"))
        mMapView.setMyLocationEnabled(false)

    }

    companion object {
        fun newInstance(latlng: LatLng): ShowMapFragment {
            var fragment = ShowMapFragment()
            var args = Bundle()
            args.putDouble("lat", latlng.latitude)
            args.putDouble("lng", latlng.longitude)
            fragment.arguments = args
            return fragment
        }
    }
}