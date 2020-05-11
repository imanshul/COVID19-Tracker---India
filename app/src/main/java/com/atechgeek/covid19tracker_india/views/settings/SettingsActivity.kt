package com.atechgeek.covid19tracker_india.views.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NavUtils
import com.atechgeek.covid19tracker_india.BuildConfig
import com.atechgeek.covid19tracker_india.R
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        loadBannerAd()
        setData()
    }

    private fun setData() {
        supportActionBar?.title = getString(R.string.action_settings)
        tvVersionName.text = "V ${BuildConfig.VERSION_NAME}"
    }

    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        adBannerView.loadAd(adRequest)
    }

    override fun onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
        super.onBackPressed()
        Log.i("anshul", "back pressed")
    }
}
