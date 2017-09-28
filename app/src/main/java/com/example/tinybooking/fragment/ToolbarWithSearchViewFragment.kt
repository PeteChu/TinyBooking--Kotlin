package com.example.tinybooking.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import com.example.tinybooking.R
import com.example.tinybooking.SearchActivity
import kotlinx.android.synthetic.main.fragment_toolbar_with_searchview.view.*

/**
 * Created by schecterza on 27/9/2017 AD.
 */

class ToolbarWithSearchViewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var rootView = inflater!!.inflate(R.layout.fragment_toolbar_with_searchview, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        setHasOptionsMenu(true)

        var toolbar : Toolbar = rootView.toolbar_with_searchview
        (activity as AppCompatActivity).setSupportActionBar(toolbar)


    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.item_searchview, menu)
        super.onCreateOptionsMenu(menu,inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var itemId = item!!.itemId

        when (itemId) {
            R.id.action_search -> {
                var intent = Intent(context, SearchActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }



    companion object {
        fun newInstance(): ToolbarWithSearchViewFragment {
            var fragment = ToolbarWithSearchViewFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}