package com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.Fragment


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crestinfosystems_jinay.crestcentralsystems.adapter.HRMSUsersListAdapter
import com.crestinfosystems_jinay.crestcentralsystems.databinding.DetsilsUserViewBinding
import com.crestinfosystems_jinay.crestcentralsystems.databinding.FragmentHrmsUsersScreenBinding
import com.crestinfosystems_jinay.crestcentralsystems.model.user
import com.crestinfosystems_jinay.crestcentralsystems.viewModel.HRMSUsersScreenViewModel

class HrmsUsersScreen : Fragment() {

    private lateinit var binding: FragmentHrmsUsersScreenBinding
    private lateinit var viewModel: HRMSUsersScreenViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            com.crestinfosystems_jinay.crestcentralsystems.R.layout.fragment_hrms_users_screen,
            container,
            false
        )
        viewModel = HRMSUsersScreenViewModel()
        val view: View = binding.root
        binding.hrmsUsersScreen = viewModel
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewAdapter.layoutManager = layoutManager
        viewModel.fetchDataFromApi { list ->
            addDataInAdapter(list)
        }
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun addDataInAdapter(list: MutableList<user>) {
        val dashboardAdapter = context?.let { context ->
            HRMSUsersListAdapter(list, context) { user ->
                dialogBox(user)
            }
        }
        binding.recyclerViewAdapter.adapter = dashboardAdapter
        binding.progressCircular.visibility = View.GONE
        binding.recyclerViewAdapter.visibility = View.VISIBLE
    }

    private fun dialogBox(user: user) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(user.first_name + " " + user.last_name)
        val daialogBinding: DetsilsUserViewBinding =
            DetsilsUserViewBinding.inflate(layoutInflater)
        builder.setView(daialogBinding.root)
        daialogBinding.empCall.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:${user.contact_number}"))
            startActivity(intent)
        }
        daialogBinding.empEmail.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:${user.email}"))
            startActivity(intent)
        }
        daialogBinding.designationName.text = user.designation_name.toString()
        daialogBinding.employeeId.text = "Employee Id - ${user.employee_number}"
        daialogBinding.reportingTo.text =
            "Reporting to - ${user.reportingTo.first_name} ${user.reportingTo.last_name}"
        builder.setPositiveButton("Ok") { dialog: DialogInterface?, which: Int ->
            dialog?.cancel()
        }
        val dialog = builder.create()
        dialog.show()
    }
}