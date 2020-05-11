package com.atechgeek.covid19tracker_india.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateTimeUtils {

    const val SERVER_DATE_FORMAT = "yyyy-MM-dd hh:mm"

    const val DEFAULT_FORMAT = "dd MMM yyyy hh:mm a"

    /*
    * Return time from millis
    */
    fun getTimeFromMillis(seconds: Long?): String? {
        return if (seconds == null || seconds == 0L) null else SimpleDateFormat(DEFAULT_FORMAT).format(seconds * 1000)
    }

    fun getDateAndTimeFromString(date:String): String {
        //2020-03-21 13:00:04
        val mytime = "Jan 17, 2012"

        val dateFormat = SimpleDateFormat(SERVER_DATE_FORMAT)

        var myDate: Date? = null
        try {
            myDate = dateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val timeFormat = SimpleDateFormat(DEFAULT_FORMAT)
       return timeFormat.format(myDate)
    }

    /*
    * Return Day from millis, like Monday/Tuesday etc.
    */
    fun getDayOfWeekFromMillis(millis: Long): String {
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = Date(millis * 1000)
        return sdf.format(date)
    }

}