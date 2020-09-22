package com.example.locationreceiverkotlin.util

import android.util.Log
import com.example.locationreceiverkotlin.interfaces.Logger

class LoggerImpl : Logger {

    override fun logd(tag: String?, message: String) {
        Log.d(tag, message)
    }
}