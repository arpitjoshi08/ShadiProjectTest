package com.test.sampleapp

import android.app.Application
import com.apilib.ApiApplication
import com.apilib.client.server.ApiFactory
import com.apilib.client.server.CallAPI
import com.apilib.persistence.PrefManager

class AppClass : Application() {

    //region -lazy values
    val apiContract: CallAPI by lazy { ApiFactory.standardClient() }
    val prefManager: PrefManager by lazy { PrefManager(this) }
    //endregion

    init {
        instance = this
    }

    //region -Companion function
    companion object {
        lateinit var instance: AppClass
    }
    //endregion

    //region -Lifecycle functions
    override fun onCreate() {
        super.onCreate()
        ApiApplication.init(this)
    }
    //endregion
}