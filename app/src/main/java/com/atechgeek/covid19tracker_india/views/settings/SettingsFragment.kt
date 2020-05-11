package com.atechgeek.covid19tracker_india.views.settings


import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.atechgeek.covid19tracker_india.R
import com.atechgeek.covid19tracker_india.utils.ThemeHelper

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.setting_pref)
        val listPref: ListPreference? = findPreference("themePref")
        listPref?.apply {

            setOnPreferenceChangeListener(object : Preference.OnPreferenceChangeListener{
                override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                    val theme:String = newValue as String
                    ThemeHelper.applyTheme(theme)
                    return true
                }
            })
        }


    }
}
