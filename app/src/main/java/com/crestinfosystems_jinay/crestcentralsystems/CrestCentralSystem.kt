package com.crestinfosystems_jinay.crestcentralsystems

import android.app.Application
import com.crestinfosystems_jinay.crestcentralsystems.utils.SharedPreferencesManager

class CrestCentralSystem : Application() {
    companion object {
        lateinit var sharedPreferencesManager: SharedPreferencesManager
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferencesManager = SharedPreferencesManager(this)
    }
}