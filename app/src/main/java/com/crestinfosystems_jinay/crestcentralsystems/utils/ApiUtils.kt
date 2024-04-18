package com.crestinfosystems_jinay.crestcentralsystems.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.crestinfosystems_jinay.crestcentralsystems.CrestCentralSystem
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ApiUtils {

    companion object {

        private val client = OkHttpClient()
        private val gson = Gson()
        private val authCode = CrestCentralSystem.sharedPreferencesManager.accessToken
        suspend fun get(url: String): Map<String, Any>? {
            return withContext(Dispatchers.IO) {
                suspendCoroutine { continuation ->
                    val client = OkHttpClient()
                    val request = Request.Builder()
                        .url(url)
                        .header("Authorization", "Bearer $authCode")
                        .build()

                    client.newCall(request).enqueue(object : Callback {
                        override fun onResponse(call: Call, response: Response) {
                            try {
                                val responseData = response.body?.string()
                                val resultMap = gson.fromJson(
                                    responseData,
                                    Map::class.java
                                ) as Map<String, Any>?
                                continuation.resume(resultMap)
                            } catch (e: Exception) {
                                continuation.resumeWithException(e)

                            }
                        }

                        override fun onFailure(call: Call, e: IOException) {
                            continuation.resumeWithException(e)

                        }
                    })
                }
            }
        }

        suspend fun launchUrlWithHeaders(
            context: Context,
            urlString: String,
        ) {
            val uri = Uri.parse(urlString)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            withContext(Dispatchers.IO) {
                try {
                    // Open a connection to the URL
                    val url = URL(urlString)
                    val connection = url.openConnection() as HttpURLConnection

                    connection.setRequestProperty("Authorization", "Bearer $authCode")
                    val responseCode = connection.responseCode
                    println(responseCode)
                    if (responseCode in 200..299) {
                        context.startActivity(intent)
                    } else {
                        // TODO Some thing Went Wrong
                    }
                    connection.disconnect()
                } catch (e: IOException) {
                    e.printStackTrace()
                    // Handle exception, such as network error
                }
            }
        }

        fun post(
            url: String,
            requestBody: RequestBody,
            callback: (Map<String, Any>?, Exception?) -> Unit
        ) {
            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    try {
                        val responseData = response.body?.string()
                        Log.d("Response", responseData.toString())
                        val resultMap =
                            gson.fromJson(responseData, Map::class.java) as Map<String, Any>?
                        callback(resultMap, null)
                    } catch (e: Exception) {
                        callback(null, e)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    callback(null, e)
                }
            })
        }
    }
}
