package com.atechgeek.covid19tracker_india.utils

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

object ThemeHelper {
    const val lightMode = "light"
    const val darkMode = "dark"
    const val default = "default"

    fun applyTheme(theme: String?) {
        when (theme) {
            lightMode -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            darkMode -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }
}