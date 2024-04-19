package com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.subscreens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crestinfosystems_jinay.crestcentralsystems.CrestCentralSystem
import com.crestinfosystems_jinay.crestcentralsystems.adapter.HolidayListAdapter
import com.crestinfosystems_jinay.crestcentralsystems.databinding.ActivityHrmsHolidaysScreenBinding
import com.crestinfosystems_jinay.crestcentralsystems.model.holiday
import com.crestinfosystems_jinay.crestcentralsystems.utils.ApiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class hrms_holidays_screen : AppCompatActivity() {
    private lateinit var binding: ActivityHrmsHolidaysScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHrmsHolidaysScreenBinding.inflate(layoutInflater)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewAdapter.layoutManager = layoutManager
        fetchData()
        binding.backPressedIcon.setOnClickListener {
            onBackPressed()
        }
        setContentView(binding.root)
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            val id = CrestCentralSystem.sharedPreferencesManager.id
            val apiUrl =
                "https://hrms.crestinfosystems.net/api/users/policies?policy_category=holidays&timezone=Asia%2FCalcutta"
            try {
                lateinit var resultMap: Map<String, Any>
                runBlocking {
                    resultMap = ApiUtils.get(apiUrl) as Map<String, Any>
                }
                if (resultMap != null && resultMap["status"] as Boolean) {
                    val applicationDetails =
                        (resultMap["data"] as List<Map<String, Any>>)
                    println(applicationDetails)
                    val listApplication = mutableListOf<holiday>()
                    for (i in applicationDetails) {
                        listApplication.add(
                            holiday(
                                id = (i["id"] as Double).toInt(),
                                holiday_reason = i["holiday_reason"].toString(),
                                holiday_date = i["holiday_date"].toString(),
                                createdBy = (i["createdBy"] as Double).toInt(),
                                updatedBy = null,
                                createdAt = i["createdAt"].toString(),
                                updatedAt = i["updatedAt"].toString()
                            )
                        )
                    }
                    withContext(Dispatchers.Main) {
                        binding.progressCircular.visibility = View.GONE
                        binding.recyclerViewAdapter.visibility = View.VISIBLE
                        var adap = HolidayListAdapter(
                            listApplication, this@hrms_holidays_screen
                        )
                        binding.recyclerViewAdapter.adapter = adap
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}