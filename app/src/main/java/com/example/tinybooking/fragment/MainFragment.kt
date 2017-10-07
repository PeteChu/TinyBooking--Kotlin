package com.example.tinybooking.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import com.example.tinybooking.R
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import com.example.tinybooking.SearchActivity
import com.example.tinybooking.adapter.FavoriteItemListAdapter
import com.example.tinybooking.dao.ListStoreInfo
import com.example.tinybooking.manager.HttpManager
import kotlinx.android.synthetic.main.fragment_book_store.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by schecterza on 9/12/2017 AD.
 */

class MainFragment : Fragment() {

    private var mSnapHelper: SnapHelper? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        var activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(rootView.main_fragment_toolbar)
        setHasOptionsMenu(true)

        mRecyclerView = rootView.list_favorite_item
        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager



        mSnapHelper = LinearSnapHelper()
        mSnapHelper!!.attachToRecyclerView(mRecyclerView)

        loadData()

    }

    fun loadData() {

        var call = HttpManager.getInstance().getService().listRepos()
        call.enqueue(object : Callback<ListStoreInfo> {
            override fun onResponse(call: Call<ListStoreInfo>?, response: Response<ListStoreInfo>?) {
                if (response!!.isSuccessful) {
                    var listStoreInfo = response.body()!!

                    mAdapter = FavoriteItemListAdapter(context, listStoreInfo)
                    mRecyclerView!!.adapter = mAdapter
                }

            }

            override fun onFailure(call: Call<ListStoreInfo>?, t: Throwable?) {

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.item_searchview, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.action_search -> {
                var intent = Intent(context, SearchActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance(): MainFragment {
            var fragment = MainFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}