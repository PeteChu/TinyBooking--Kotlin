package com.example.tinybooking.fragment

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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_map, container, false)
        initInstances(rootView)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        var location = ShowMapFragment().arguments["latlng"]

        Toast.makeText(context, location.toString(), Toast.LENGTH_SHORT).show()
    }

    fun initInstances(rootView: View) {

        //        Initialize MapView
        mMapView = rootView.show_map_view
        mMapView.setOnMapInitializedListener(this)
        mMapView.initialize(childFragmentManager)

    }

    override fun onMapInitialized() {

    }

    companion object {
        fun newInstance(latlng: LatLng): ShowMapFragment {
            var fragment = ShowMapFragment()
            var args = Bundle()
            args.putString("latlng", latlng.toString())
            fragment.arguments = args
            return fragment
        }
    }
}