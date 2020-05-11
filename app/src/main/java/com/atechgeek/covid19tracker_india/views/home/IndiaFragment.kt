package com.atechgeek.covid19tracker_india.views.home


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.atechgeek.covid19tracker_india.R
import com.atechgeek.covid19tracker_india.base.BaseFragment
import com.atechgeek.covid19tracker_india.constants.PrefNames
import com.atechgeek.covid19tracker_india.models.AllData
import com.atechgeek.covid19tracker_india.utils.AddHelper
import com.atechgeek.covid19tracker_india.utils.AnimationUtils
import com.atechgeek.covid19tracker_india.utils.DateTimeUtils
import com.atechgeek.covid19tracker_india.utils.Prefs
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.content_details.*
import kotlinx.android.synthetic.main.fragment_india.*


class IndiaFragment : BaseFragment() {

    var viewModel: HomeViewModel? = null

    override fun getContentLayoutResId(): Int = R.layout.fragment_india

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initChart()
        addObserver()
        setPullToRefresh()
        loadBannerAd()
    }

    private fun addObserver() {
        if (viewModel != null) return

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel?.getAllCovidLiveData()?.observe(this, Observer {
            swipeRefreshIndiaData.isRefreshing = false
            setData(it.data.allData.last())
            Prefs.with(context)?.save(PrefNames.ALL_DATA, it.data.allData.last())
        })

        viewModel?.getAllCovidErrorLiveData()?.observe(this, Observer {
            swipeRefreshIndiaData.isRefreshing = false
            setData(Prefs.with(context)?.getObject(PrefNames.ALL_DATA, AllData::class.java))
            Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT)
                .show()
        })

        loadAllData()
    }

    @SuppressLint("SetTextI18n")
    private fun setData(last: AllData?) {
        last?.apply {
            AnimationUtils.animateTextView(0, confirmed, tvConfirmedCasesCount)
            AnimationUtils.animateTextView(0, recovered, tvRecoveredCasesCount)
            AnimationUtils.animateTextView(0, deaths, tvDeathCasesCount)
            AnimationUtils.animateTextView(0, casesMohfw, tvCasesAsPerMohfw)
            AnimationUtils.animateTextView(0, treatmentOngoing, tvTreatmentOngoing)
            AnimationUtils.animateTextView(0, statesAffected, tvStatesInfected)
            AnimationUtils.animateTextView(0, totalTestDone, tvTestsDone)
            tvLastUpdated.text =
                "${getString(R.string.last_updated)} ${DateTimeUtils.getDateAndTimeFromString(
                    timestamp
                )}"

            setChartData(confirmed, recovered, deaths)
        }
    }


    private fun setPullToRefresh() {
        swipeRefreshIndiaData.setOnRefreshListener {
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                loadAllData()
            }
        }
    }

    private fun loadAllData() {
        viewModel?.getAllData()
    }

    private fun initChart() {
        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false

        pieChart.rotationAngle = 0f
        pieChart.animateY(1400, Easing.EaseInOutQuad)
        pieChart.setDrawEntryLabels(false)

        pieChart.holeRadius = 60f
        pieChart.transparentCircleRadius = 61f

        pieChart.setHoleColor(ContextCompat.getColor(context!!, android.R.color.transparent))
    }

    private fun setChartData(confirmed: Int, recoverd: Int, deaths: Int) {

        val colors = ArrayList<Int>()
        val pieEntryList = ArrayList<PieEntry>()

        val total = confirmed + recoverd + deaths

        if (total == 0) return

        val confirmedPercent = confirmed * 100 / total
        val recoveredPercent = recoverd * 100 / total
        val deathPercent = deaths * 100 / total

        if (confirmed != 0) {
            pieEntryList.add(PieEntry(confirmedPercent.toFloat(), "${confirmedPercent}%"))
            colors.add(ContextCompat.getColor(context!!, R.color.orange))
        }

        if (recoverd != 0) {
            pieEntryList.add(PieEntry(recoveredPercent.toFloat(), "${recoveredPercent}%"))
            colors.add(ContextCompat.getColor(context!!, R.color.green))
        }

        if (deaths != 0) {
            pieEntryList.add(PieEntry(deathPercent.toFloat(), "${deathPercent}%"))
            colors.add(ContextCompat.getColor(context!!, R.color.red))
        }


        val dataSet = PieDataSet(pieEntryList, "")
        dataSet.colors = colors
        dataSet.setDrawIcons(false)
        dataSet.setDrawValues(false)


        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(6f)
        data.setValueTextColor(Color.WHITE)

        pieChart.data = data
        pieChart.animateXY(1000, 1000)
    }

    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        adBannerView.loadAd(adRequest)
    }

    override fun onInterstitialAdClosed() {
        super.onInterstitialAdClosed()
        Log.i(AddHelper.ADD_TAG, "onMyAdClosed")
        loadAllData()
    }
}