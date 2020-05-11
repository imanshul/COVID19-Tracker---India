package com.atechgeek.covid19tracker_india.views.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.atechgeek.covid19tracker_india.R
import com.atechgeek.covid19tracker_india.base.BaseFragment
import com.atechgeek.covid19tracker_india.constants.PrefNames
import com.atechgeek.covid19tracker_india.models.StateData
import com.atechgeek.covid19tracker_india.models.StateListData
import com.atechgeek.covid19tracker_india.utils.AddHelper
import com.atechgeek.covid19tracker_india.utils.Prefs
import com.atechgeek.covid19tracker_india.views.home.adapter.StateListAdapter
import kotlinx.android.synthetic.main.fragment_state_wise.*
import java.util.*
import kotlin.collections.ArrayList


class StateWiseFragment : BaseFragment(), View.OnClickListener {

    var viewModel: HomeViewModel? = null

    val adapter by lazy { StateListAdapter() }

    val allStateDataList = ArrayList<StateData>()

    override fun getContentLayoutResId(): Int = R.layout.fragment_state_wise

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setAdapter()
        setTextListner()
        setClicks()
        addObserver()
        setPullToRefresh()
    }


    private fun addObserver() {
        if (viewModel != null) return

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel?.getStateCovidLiveData()?.observe(this, Observer {
            swipeRefreshStateData.isRefreshing = false
            //Set Data after sorting
            setStateWiseData(it.data.stateData)

            //save data to cache
            Prefs.with(context)?.save(PrefNames.STATE_DATA, it.data)
        })

        viewModel?.getStateCovidErrorLiveData()?.observe(this, Observer {
            swipeRefreshStateData.isRefreshing = false
            Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT)
                .show()

            //Load cached Data
            val myCacheData =
                Prefs.with(context)?.getObject(PrefNames.STATE_DATA, StateListData::class.java)
            myCacheData?.apply {
                setStateWiseData(this.stateData)
            }

        })

        loadStateData()
    }

    private fun setStateWiseData(stateData: List<StateData>) {
        allStateDataList.clear()
        allStateDataList.addAll(stateData.sortedByDescending { it.confirmedCases })
        adapter.addData(allStateDataList)
    }

    private fun setAdapter() {
        rvStateData.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvStateData.adapter = adapter
    }

    private fun setClicks() {
        ivClear.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            ivClear -> {
                etSearch.text.clear()
            }
        }
    }

    private fun setPullToRefresh() {
        swipeRefreshStateData.setOnRefreshListener {
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                loadStateData()
            }
        }
    }

    private fun loadStateData() {
        viewModel?.getSatateWiseData()
    }


    private fun setTextListner() {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchText(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    ivClear.visibility = View.GONE
                } else {
                    ivClear.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun searchText(s: String) {
        if (s.isEmpty()) {
            adapter.addData(allStateDataList)
        } else {
            val list = ArrayList<StateData>()
            list.addAll(allStateDataList.filter {
                it.name.toLowerCase(Locale.getDefault()).contains(s)
            })
            adapter.addData(list)
        }
    }

    override fun onInterstitialAdClosed() {
        super.onInterstitialAdClosed()
        Log.i(AddHelper.ADD_TAG, "onMyAdClosed")
        loadStateData()
    }

}
