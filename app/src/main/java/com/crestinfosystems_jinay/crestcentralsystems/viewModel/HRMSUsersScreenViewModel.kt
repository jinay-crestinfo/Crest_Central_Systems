package com.crestinfosystems_jinay.crestcentralsystems.viewModel

import androidx.lifecycle.ViewModel
import com.crestinfosystems_jinay.crestcentralsystems.model.ReportingTo
import com.crestinfosystems_jinay.crestcentralsystems.model.user
import com.crestinfosystems_jinay.crestcentralsystems.utils.ApiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class HRMSUsersScreenViewModel : ViewModel() {

    fun fetchDataFromApi(onSuccess: (MutableList<user>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiUrl =
                "https://hrms.crestinfosystems.net/api/users?query=&status=active&filter=&timezone=Asia%2FCalcutta"
            try {
                lateinit var resultMap: Map<String, Any>
                runBlocking {
                    resultMap = ApiUtils.get(apiUrl) as Map<String, Any>
                }
                if (resultMap != null && resultMap["status"] as Boolean) {
                    val applicationDetails =
                        (resultMap["data"] as Map<String, Any>)["Allusers"] as List<Map<String, Any>>
                    println(applicationDetails)
                    val listApplication = mutableListOf<user>()
                    for (i in applicationDetails) {
                        listApplication.add(
                            user(
                                id = (i["id"] as Double).toInt(),
                                first_name = i["first_name"].toString(),
                                last_name = i["last_name"].toString(),
                                reportingTo = ReportingTo(
                                    first_name = (i["reportingTo"] as Map<String, Any>)["first_name"].toString(),
                                    last_name = (i["reportingTo"] as Map<String, Any>)["last_name"].toString(),
                                    id = ((i["reportingTo"] as Map<String, Any>)["id"] as Double).toInt()
                                ),
                                designation_id = (i["designation_id"] as Double).toInt(),
                                email = i["email"].toString(),
                                status = i["status"].toString(),
                                employee_number = i["employee_number"].toString(),
                                contact_number = i["contact_number"].toString(),
                                alternate_contact_number = i["alternate_contact_number"].toString(),
                                role = (i["role"] as Double).toInt(),
                                joining_date = i["joining_date"].toString(),
                                designation_name = i["designation_name"].toString(),
                                role_name = i["role_name"].toString(),
                                onboarding_status = i["onboarding_status"].toString()
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

//    {id=1.0,
//    first_name=super,
//    last_name=admin,
//    designation_id=34.0,
//    email=admin@crestinfosystems.com, status=active, employee_number=E001, contact_number=9925787865, alternate_contact_number=8511869145, role=11.0, joining_date=2010-01-04, reportingTo={id=1.0, first_name=super, last_name=admin}, designation_name=Admin Manager, role_name=Admin, onboarding_status=complete}
}