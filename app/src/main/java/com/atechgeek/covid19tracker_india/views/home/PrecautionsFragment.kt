package com.atechgeek.covid19tracker_india.views.home


import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.atechgeek.covid19tracker_india.R
import com.atechgeek.covid19tracker_india.base.BaseFragment
import com.atechgeek.covid19tracker_india.extensions.openWebUrl
import com.atechgeek.covid19tracker_india.utils.AddHelper
import kotlinx.android.synthetic.main.fragment_precautions.*


/**
 * A simple [Fragment] subclass.
 */
class PrecautionsFragment : BaseFragment(), View.OnClickListener {

    override fun getContentLayoutResId(): Int = R.layout.fragment_precautions

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setData()
        setClicks()
    }

    private fun setClicks() {
        btnReadFullArticle.setOnClickListener(this)
    }

    private fun setData() {
        val sp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(
                getString(R.string.preventive_measure_long),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        } else {
            Html.fromHtml(getString(R.string.preventive_measure_long))
        }
        tvPrecautionDesc.setText(sp)
    }

    override fun onClick(v: View?) {
        when (v) {
            btnReadFullArticle -> {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    openWebUrl(context!!, getString(R.string.advice_for_public_link))
                }
            }
        }
    }

    override fun onInterstitialAdClosed() {
        super.onInterstitialAdClosed()
        Log.i(AddHelper.ADD_TAG, "onMyAdClosed Intersitial Precaution")
        openWebUrl(context!!, getString(R.string.advice_for_public_link))
    }
}
