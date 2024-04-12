package com.crestinfosystems_jinay.crestcentralsystems.viewModel

import androidx.lifecycle.ViewModel
import com.crestinfosystems_jinay.crestcentralsystems.CrestCentralSystem
import com.crestinfosystems_jinay.crestcentralsystems.model.dashboard_application
import com.crestinfosystems_jinay.crestcentralsystems.utils.ApiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DashboardViewModel : ViewModel() {

    fun fetchData(onSuccess: (MutableList<dashboard_application>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val id = CrestCentralSystem.sharedPreferencesManager.id
            val apiUrl =
                "https://central.crestinfosystems.net/api/users/${id.toString()}/applications"
            try {
                lateinit var resultMap: Map<String, Any>
                runBlocking {
                    resultMap = ApiUtils.get(apiUrl) as Map<String, Any>
                }
                if (resultMap != null && resultMap["status"] as Boolean) {
                    val applicationDetails =
                        (resultMap["data"] as Map<String, Any>)["application_details"] as List<Map<String, Any>>
                    println(applicationDetails)
                    val listApplication = mutableListOf<dashboard_application>()
                    for (i in applicationDetails) {
                        listApplication.add(
                            dashboard_application(
                                app_id = (i["app_id"] as Double).toInt(),
                                application_name = i["application_name"].toString(),
                                app_key = i["app_key"].toString(),
                                app_url = i["app_url"].toString(),
                                designation = i["designation"],
                                img_path = i["img_path"].toString()
                            )
                        )
                    }
                    withContext(Dispatchers.Main) {
                        onSuccess(listApplication)
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}