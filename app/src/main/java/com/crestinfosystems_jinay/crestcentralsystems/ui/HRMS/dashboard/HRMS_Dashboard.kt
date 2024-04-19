package com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.dashboard

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.crestinfosystems_jinay.crestcentralsystems.CrestCentralSystem
import com.crestinfosystems_jinay.crestcentralsystems.R
import com.crestinfosystems_jinay.crestcentralsystems.databinding.ActivityHrmsDashboardBinding
import com.crestinfosystems_jinay.crestcentralsystems.ui.Auth.LoginActivity
import com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.Fragment.HrmsHomeScreen
import com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.Fragment.HrmsLeavesScreen
import com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.Fragment.HrmsPoliciesScreen
import com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.Fragment.HrmsUsersScreen
import com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.subscreens.hrms_holidays_screen
import com.crestinfosystems_jinay.crestcentralsystems.viewModel.HRMSDashboardViewModel

class HRMS_Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityHrmsDashboardBinding
    private val homeFragment = HrmsHomeScreen()
    private val leavesFragment = HrmsLeavesScreen()
    private val policiesFragment = HrmsPoliciesScreen()
    private val usersFragment = HrmsUsersScreen()
    private val viewModel: HRMSDashboardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hrms_dashboard)
        binding.lifecycleOwner = this
        binding.hrmsDashboardViewModel = viewModel
        setBottomNavBarListner()
        setnavDrawer()
        setCurrentFragment(homeFragment)
        binding.drawerIcon.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }


    }

    private fun setBottomNavBarListner() {
        binding.tab1.setOnClickListener {
            setCurrentFragment(homeFragment)
            setScreenIndicator(1)
        }
        binding.tab2.setOnClickListener {
            setCurrentFragment(leavesFragment)
            setScreenIndicator(2)
        }
        binding.tab3.setOnClickListener {
            setCurrentFragment(policiesFragment)
            setScreenIndicator(3)
        }
        binding.tab4.setOnClickListener {
            setCurrentFragment(usersFragment)
            setScreenIndicator(4)
        }
    }

    private fun setScreenIndicator(currentScreen: Int) {
        when (currentScreen) {
            1 -> {
                binding.tab1.setColorFilter(
                    ContextCompat.getColor(this, R.color.selected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab2.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab3.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab4.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )
            }

            2 -> {
                binding.tab2.setColorFilter(
                    ContextCompat.getColor(this, R.color.selected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab1.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab3.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab4.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )

            }

            3 -> {
                binding.tab3.setColorFilter(
                    ContextCompat.getColor(this, R.color.selected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab2.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab1.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab4.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )

            }

            4 -> {
                binding.tab4.setColorFilter(
                    ContextCompat.getColor(this, R.color.selected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab2.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab3.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )
                binding.tab1.setColorFilter(
                    ContextCompat.getColor(this, R.color.unselected_icon),
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    private fun setnavDrawer() {
        val headerView = binding.navView.getHeaderView(0)
        val username = headerView.findViewById<TextView>(R.id.user_name)
        username.text = CrestCentralSystem.sharedPreferencesManager.userName
        val useremail = headerView.findViewById<TextView>(R.id.user_email)
        useremail.text = CrestCentralSystem.sharedPreferencesManager.email

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.hrms_drawer_signout -> {
                    CrestCentralSystem.sharedPreferencesManager.accessToken = null
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    false
                }

                R.id.hrms_drawer_holiday -> {
                    val intent = Intent(this, hrms_holidays_screen::class.java)
                    startActivity(intent)
                    false
                }

                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}