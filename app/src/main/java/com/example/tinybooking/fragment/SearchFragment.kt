package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tinybooking.R
import com.example.tinybooking.adapter.SearchItemListAdapter
import com.example.tinybooking.dao.ListStoreInfo
import com.example.tinybooking.manager.HttpManager
import kotlinx.android.synthetic.main.fragment_book.view.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by schecterza on 27/9/2017 AD.
 */

class SearchFragment : Fragment() {

    lateinit var listStoreInfo: ListStoreInfo

    lateinit var mRecyclerView: RecyclerView
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    lateinit var mAdapter: RecyclerView.Adapter<*>


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater!!.inflate(R.layout.fragment_search, container, false)
        initInstances(rootview)
        return rootview
    }

    fun initInstances(rootView: View) {

        var toolbar = rootView.search_fragment_toolbar
        var activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(rootView.search_fragment_toolbar)
        var actionBar = activity.supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }



        mRecyclerView = rootView.list_search_item
        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(context)
        mRecyclerView!!.layoutManager = mLayoutManager

        loadData()
    }

    private fun loadData() {

        var call = HttpManager.getInstance().getService().listRepos()
        call.enqueue(object : Callback<ListStoreInfo> {

            override fun onResponse(call: Call<ListStoreInfo>?, response: Response<ListStoreInfo>?) {
                if (response!!.isSuccessful) {
                    listStoreInfo = response.body()!!
                    setRecyclerView(listStoreInfo)
                }
            }

            override fun onFailure(call: Call<ListStoreInfo>?, t: Throwable?) {
                Toast.makeText(context, t!!.toString(), Toast.LENGTH_LONG).show()
            }

        })


    }

    fun setRecyclerView(listStoreInfo: ListStoreInfo) {
        mAdapter = SearchItemListAdapter(context, listStoreInfo)
        mRecyclerView!!.adapter = mAdapter
    }


    companion object {
        fun newInstance(): SearchFragment {
            var fragment = SearchFragment()
            var args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
}