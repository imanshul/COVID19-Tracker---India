package com.atechgeek.covid19tracker_india

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.atechgeek.covid19tracker_india.utils.ThemeHelper
import com.google.android.gms.ads.MobileAds

class MyApplication() : Application() {
    override fun onCreate() {
        super.onCreate()

        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        val theme: String? = sharedPreferences.getString("themePref", ThemeHelper.default)
        ThemeHelper.applyTheme(theme)

        MobileAds.initialize(this, getString(R.string.appUnitId))
    }
}