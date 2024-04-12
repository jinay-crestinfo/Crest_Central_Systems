package com.crestinfosystems_jinay.crestcentralsystems.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token"
        private const val EMAIL = "email"
        private const val USER_NAME = "user_name"
        private const val ID = "id"
        // Define other keys for your shared preferences here
    }

    var accessToken: String?
        get() = sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
        set(value) {
            editor.putString(ACCESS_TOKEN_KEY, value)
            editor.apply()
        }

    var email: String?
        get() = sharedPreferences.getString(EMAIL, null)
        set(value) {
            editor.putString(EMAIL, value)
            editor.apply()
        }

    var userName: String?
        get() = sharedPreferences.getString(USER_NAME, null)
        set(value) {
            editor.putString(USER_NAME, value)
            editor.apply()
        }

    var id: String?
        get() = sharedPreferences.getString(ID, null)
        set(value) {
            editor.putString(ID, value)
            editor.apply()
        }

    // Define other shared preferences properties and functions as needed
}