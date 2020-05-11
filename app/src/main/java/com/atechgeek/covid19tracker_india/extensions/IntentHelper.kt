package com.atechgeek.covid19tracker_india.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.atechgeek.covid19tracker_india.R

/**
 * Created by Anshul Thakur on 22/3/20.
 */

fun openWebUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

fun launchMarket(context: Context) {
    val uri = Uri.parse("market://details?id=" + context.getPackageName())
    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
    try {
        context.startActivity(myAppLinkToMarket)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, " unable to find market app", Toast.LENGTH_LONG).show()
    }

}