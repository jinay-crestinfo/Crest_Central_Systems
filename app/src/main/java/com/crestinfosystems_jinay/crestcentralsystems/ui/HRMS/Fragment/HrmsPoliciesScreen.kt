package com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crestinfosystems_jinay.crestcentralsystems.adapter.HRMSPoliciesAdapter
import com.crestinfosystems_jinay.crestcentralsystems.databinding.FragmentHrmsPoliciesScreenBinding
import com.crestinfosystems_jinay.crestcentralsystems.model.Policies
import com.crestinfosystems_jinay.crestcentralsystems.utils.ApiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class HrmsPoliciesScreen : Fragment() {
    lateinit var binding: FragmentHrmsPoliciesScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHrmsPoliciesScreenBinding.inflate(layoutInflater)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewAdapter.layoutManager = layoutManager
        fetchData()
        return binding.root
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiUrl =
                "https://hrms.crestinfosystems.net/api/users/policies?policy_category=Company+Policies&timezone=Asia%2FCalcutta"
            try {
                lateinit var resultMap: Map<String, Any>
                runBlocking {
                    resultMap = ApiUtils.get(apiUrl) as Map<String, Any>
                }
                if (resultMap != null && resultMap["status"] as Boolean) {
                    val applicationDetails =
                        (resultMap["data"] as List<Map<String, Any>>)
                    println(applicationDetails)
                    val listApplication = mutableListOf<Policies>()
                    for (i in applicationDetails) {
                        listApplication.add(
                            Policies(
                                title = i["policy_title"].toString(),
                                details = i["policy_detail"].toString()
                            )
                        )
                    }
                    withContext(Dispatchers.Main) {
                        val dashboardAdapter = context?.let { context ->
                            HRMSPoliciesAdapter(listApplication, context)
                        }
                        binding.recyclerViewAdapter.adapter = dashboardAdapter
                        binding.progressCircular.visibility = View.GONE
                        binding.recyclerViewAdapter.visibility = View.VISIBLE
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }


}