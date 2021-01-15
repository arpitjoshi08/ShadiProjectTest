package com.apilib

import android.annotation.SuppressLint
import android.content.Context


@SuppressLint("StaticFieldLeak")
object ApiApplication {
    lateinit var context: Context
    fun init(context: Context) {
        if (!this::context.isInitialized) {
            this.context = context
        }
    }
}