package com.crestinfosystems_jinay.crestcentralsystems.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crestinfosystems_jinay.crestcentralsystems.CrestCentralSystem
import com.crestinfosystems_jinay.crestcentralsystems.R
import com.crestinfosystems_jinay.crestcentralsystems.adapter.DashboardAdapter
import com.crestinfosystems_jinay.crestcentralsystems.databinding.ActivityDashboardBinding
import com.crestinfosystems_jinay.crestcentralsystems.ui.Profile.UserProfile
import com.crestinfosystems_jinay.crestcentralsystems.viewModel.DashboardViewModel

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        binding.lifecycleOwner = this
        binding.dashboardViewModel = viewModel
        binding.floatingActionButton.text = CrestCentralSystem.sharedPreferencesManager.userName
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewAdapter.layoutManager = layoutManager
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }
        viewModel.fetchData { list ->
            binding.progressCircular.visibility = View.GONE
            binding.recyclerViewAdapter.visibility = View.VISIBLE
            val dashboardAdapter = DashboardAdapter(list, this@Dashboard)
            binding.recyclerViewAdapter.adapter = dashboardAdapter
        }
    }
}