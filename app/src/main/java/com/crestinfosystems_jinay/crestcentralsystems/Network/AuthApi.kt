package com.crestinfosystems_jinay.crestcentralsystems.Network

import android.util.Log
import com.crestinfosystems_jinay.crestcentralsystems.CrestCentralSystem
import com.crestinfosystems_jinay.crestcentralsystems.utils.ApiUtils
import okhttp3.FormBody

class AuthApi {
    companion object {
        fun login(
            email: String,
            password: String,
            onSuccess: (Map<String, Any>?) -> Unit,
            onError: () -> Unit
        ) {
            val apiUrl = "https://hrms.crestinfosystems.net/api/users/login"
            val requestBody = FormBody.Builder()
                .add("email", email.trim())
                .add("password", password.trim())
                .add("rememberMe", false.toString())
                .build()

            ApiUtils.post(apiUrl, requestBody) { resultMap, exception ->
                if (exception != null) {
                    onError()
                    exception.printStackTrace()
                } else {
                    // Process response data
                    if (resultMap != null && resultMap["status_code"] == 200.0) {
                        Log.d("Login Data", "Login Success")
                        var dataList: List<Any> = resultMap["data"] as List<Any>
                        var data: Map<String, Any> = dataList[0] as Map<String, Any>
                        CrestCentralSystem.sharedPreferencesManager.accessToken =
                            data["access_token"].toString()
                        CrestCentralSystem.sharedPreferencesManager.id =
                            (data["user"] as Map<String, Any>)["id"].toString()
                        CrestCentralSystem.sharedPreferencesManager.email =
                            (data["user"] as Map<String, Any>)["email"].toString()
                        CrestCentralSystem.sharedPreferencesManager.userName =
                            ((data["user"] as Map<String, Any>)["first_name"].toString() + " " + (data["user"] as Map<String, Any>)["last_name"].toString())
                        onSuccess(resultMap)
                    }
                }
            }
        }
    }
}