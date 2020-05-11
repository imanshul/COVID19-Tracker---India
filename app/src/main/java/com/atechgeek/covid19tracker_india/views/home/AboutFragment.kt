package com.atechgeek.covid19tracker_india.views.home


import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.text.HtmlCompat

import com.atechgeek.covid19tracker_india.R
import com.atechgeek.covid19tracker_india.base.BaseFragment
import com.atechgeek.covid19tracker_india.extensions.openWebUrl
import com.atechgeek.covid19tracker_india.utils.AddHelper
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment(), View.OnClickListener {

    override fun getContentLayoutResId(): Int = R.layout.fragment_about

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        playAnimation()
        setData()
        setClicks()
        loadBannerAd()
    }

    private fun playAnimation() {
        animViewAbout.setAnimation(R.raw.programming)
        animViewAbout.playAnimation()
    }

    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        adBannerView.loadAd(adRequest)
    }

    private fun setData() {
        //Credits
        val sp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(
                getString(R.string.credits_desc),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        } else {
            Html.fromHtml(getString(R.string.credits_desc))
        }
        tvCreditsDescription.setText(sp)
        tvCreditsDescription.setMovementMethod(LinkMovementMethod.getInstance())

        //About Author
        val sp2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(
                getString(R.string.about_author),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        } else {
            Html.fromHtml(getString(R.string.about_author))
        }
        tvAboutAuthor.setText(sp2)
        tvAboutAuthor.setMovementMethod(LinkMovementMethod.getInstance())

    }

    private fun setClicks() {
        btnContactMe.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when (v) {

            btnContactMe -> {
                if (mRewardedVideoAd.isLoaded) {
                    mRewardedVideoAd.show()
                } else {
                    openWebUrl(context!!, getString(R.string.contact_form_url))
                }
            }
        }
    }

    override fun onMyRewardedAdClosed(isRewarded: Boolean) {
        Log.i(AddHelper.ADD_TAG, "onMyAdRewarded About")
        if (isRewarded) {
            openWebUrl(context!!, getString(R.string.contact_form_url))
        } else {
            Toast.makeText(context!!, "Please watch full video", Toast.LENGTH_LONG).show()
        }
        super.onMyRewardedAdClosed(isRewarded)

    }
}


