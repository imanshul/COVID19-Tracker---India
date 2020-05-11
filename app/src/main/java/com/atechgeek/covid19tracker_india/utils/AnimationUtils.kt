package com.atechgeek.covid19tracker_india.utils

import android.animation.ValueAnimator
import android.widget.TextView

/**
 * Created by Anshul Thakur on 19/3/20.
 */

object AnimationUtils {

    fun animateTextView(initialValue: Int, finalValue: Int, textview: TextView) {
        val valueAnimator = ValueAnimator.ofInt(initialValue, finalValue)
        valueAnimator.duration = 1500
        valueAnimator.addUpdateListener { valueAnimator ->
            textview.text = valueAnimator.animatedValue.toString()
        }
        valueAnimator.start()
    }
}