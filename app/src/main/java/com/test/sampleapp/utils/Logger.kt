package com.test.sampleapp.utils

import android.util.Log

class Logger {
    companion object {
        fun loggerE(tag: String, message: String) {
            Log.e(tag, message)
        }

        fun loggerD(tag: String, message: String) {
            Log.d(tag, message)
        }

        fun loggerV(tag: String, message: String) {
            Log.v(tag, message)
        }
    }
}