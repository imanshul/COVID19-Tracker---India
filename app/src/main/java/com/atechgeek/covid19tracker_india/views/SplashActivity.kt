package com.atechgeek.covid19tracker_india.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.atechgeek.covid19tracker_india.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY = 5000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        playAnimation()

        Handler().postDelayed({
            gotoNextActvity()
        }, SPLASH_DELAY.toLong())

    }

    private fun playAnimation() {
        animViewSplash.setAnimation(R.raw.wuhan_corona_spreading_out)
        animViewSplash.playAnimation()
    }

    private fun gotoNextActvity() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
