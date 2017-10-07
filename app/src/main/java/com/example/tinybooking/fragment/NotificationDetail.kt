package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx
import com.example.tinybooking.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_notificationdetail.view.*
import kotlinx.android.synthetic.main.pullzoom_content.view.*
import kotlinx.android.synthetic.main.pullzoom_zoom.view.*

class NotificationDetail : Fragment(){
        lateinit var page : PullToZoomScrollViewEx
        lateinit var urlImage : String
        lateinit var title : String
        lateinit var image : ImageView
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater!!.inflate(R.layout.fragment_notificationdetail, container, false)
        initInstances(rootview)
        return rootview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var Data = arguments.get("data") as Bundle
        urlImage = Data.getString("url")
        title = Data.getString("title")
        super.onCreate(savedInstanceState)
    }

    fun initInstances(rootview : View){
        var head : View
        var zoom : View
        var content : View
        page = rootview.noti_detail_area
        head = LayoutInflater.from(context).inflate(R.layout.pullzoom_head,null,false)
        zoom = LayoutInflater.from(context).inflate(R.layout.pullzoom_zoom,null,false)
        content = LayoutInflater.from(context).inflate(R.layout.pullzoom_content,null,false)
        Picasso.with(context).load(urlImage).into(zoom.iv_zoom)
        content.zoom_title.text = title
        page.headerView = head
        page.zoomView = zoom
        page.setScrollContentView(content)



        val localDisplayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(localDisplayMetrics)
        val mScreenHeight = localDisplayMetrics.heightPixels
        val mScreenWidth = localDisplayMetrics.widthPixels
        val localObject = LinearLayout.LayoutParams(mScreenWidth, (9.0f * (mScreenWidth / 16.0f)).toInt())
        page.setHeaderLayoutParams(localObject)

    }
    companion object {
        fun newInstance(fieldData: Bundle): NotificationDetail {
            var fragment = NotificationDetail()
            var args = Bundle()
            args.putBundle("data", fieldData)
            fragment.arguments = args

            return fragment
        }
    }
}