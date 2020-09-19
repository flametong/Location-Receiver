package com.example.locationreceiverkotlin.util

import java.util.*

object Util {

    // Get millis of 12 p.m. of current date
    fun getMillisOfCurrentDate(): Long {
        return GregorianCalendar(
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).timeInMillis
    }
}