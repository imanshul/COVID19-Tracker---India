package com.atechgeek.covid19tracker_india.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.atechgeek.covid19tracker_india.R
import com.atechgeek.covid19tracker_india.utils.AddHelper
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener

/**
 * Created by Anshul Thakur on 21/3/20.
 */

abstract class BaseFragment : Fragment() {

    var baseView: View? = null

    lateinit var mInterstitialAd: InterstitialAd
    lateinit var mRewardedVideoAd: RewardedVideoAd

    private var isRewarded:Boolean = false

    @LayoutRes
    protected abstract fun getContentLayoutResId(): Int


    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {

        if (baseView == null) {
            baseView = inflater.inflate(getContentLayoutResId(), container, false)
        }

        return baseView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mInterstitialAd = InterstitialAd(context)
        loadInterstitialAd(getString(R.string.interstial_add_id))

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context)

        loadRewardedVideoAd()
    }

    //Intertial Add
    private fun loadInterstitialAd(interstitialAdUnitId: String) {
        mInterstitialAd.adUnitId = interstitialAdUnitId
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        runInterstitialAdEvents()
    }

    open fun onInterstitialAdClosed() {

    }

    private fun runInterstitialAdEvents() {
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClicked() {
                super.onAdOpened()
                mInterstitialAd.adListener.onAdClosed()
            }

            override fun onAdClosed() {
                onInterstitialAdClosed()
            }
        }
    }

    //Reward Ad
    private fun loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.rewarded_add_id), AdRequest.Builder().build())
        runRewardAdEvents()
    }

    open fun onMyRewardedAdClosed(isRewarded:Boolean) {
        Log.i(AddHelper.ADD_TAG,"Reward Video is closed--base")
    }

    private fun runRewardAdEvents() {
        mRewardedVideoAd.rewardedVideoAdListener = object : RewardedVideoAdListener {
            override fun onRewardedVideoAdClosed() {
                onMyRewardedAdClosed(isRewarded)
                Log.i(AddHelper.ADD_TAG, "onRewardedVideoAdClosed==")
                loadRewardedVideoAd()
                isRewarded = false
            }

            override fun onRewardedVideoAdLeftApplication() {
                Log.i(AddHelper.ADD_TAG, "onRewardedVideoAdLeftApplication")
            }

            override fun onRewardedVideoAdLoaded() {
                Log.i(AddHelper.ADD_TAG, "onRewardedVideoAdLoaded")
            }

            override fun onRewardedVideoAdOpened() {
                Log.i(AddHelper.ADD_TAG, "onRewardedVideoAdOpened")
            }

            override fun onRewardedVideoCompleted() {
                Log.i(AddHelper.ADD_TAG, "onRewardedVideoCompleted")
            }

            override fun onRewarded(p0: RewardItem?) {
                Log.i(AddHelper.ADD_TAG, "onRewarded Parent Base Fragment")
                isRewarded = true
            }

            override fun onRewardedVideoStarted() {
                Log.i(AddHelper.ADD_TAG, "onRewardedVideoStarted")
            }

            override fun onRewardedVideoAdFailedToLoad(p0: Int) {
                Log.i(AddHelper.ADD_TAG, "onRewardedVideoAdFailedToLoad")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mRewardedVideoAd.pause(context)
    }

    override fun onResume() {
        super.onResume()
        mRewardedVideoAd.resume(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRewardedVideoAd.destroy(context)
    }

}