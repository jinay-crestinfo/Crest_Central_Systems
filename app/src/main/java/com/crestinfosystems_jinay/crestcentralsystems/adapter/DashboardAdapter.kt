package com.crestinfosystems_jinay.crestcentralsystems.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.crestinfosystems_jinay.crestcentralsystems.databinding.DashboardAppliactionTileBinding
import com.crestinfosystems_jinay.crestcentralsystems.model.dashboard_application
import com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.dashboard.HRMS_Dashboard
import com.crestinfosystems_jinay.crestcentralsystems.utils.ApiUtils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DashboardAdapter(
    private var items: List<dashboard_application>,
    var context: Context,
) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: DashboardAppliactionTileBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            DashboardAppliactionTileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            if (items[position].app_key == "hrms") {
                var intent = Intent(context, HRMS_Dashboard::class.java)
                startActivity(context, intent, null)
            }
//            else if (items[position].app_key == "timetracker") {
//
//            }
//            else if (items[position].app_key == "assettracker") {
//            }
            else {
                dialog(items[position].app_url)
            }

        }
        with(holder)
        {
            with(items[position]) {
                binding.title.text = items[position].application_name
                Picasso.get().load(items[position].img_path).into(binding.icon)
            }
        }
    }

    fun submitList(newData: List<dashboard_application>) {
        items = newData
        notifyDataSetChanged()
    }

    private fun dialog(url: String) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("This Feature currently not available on App. Want to processed with WebView?")
        builder.setTitle("Alert !")
        builder.setCancelable(false)
        builder.setPositiveButton(
            "Yes"
        ) { dialog: DialogInterface?, which: Int ->
            CoroutineScope(Dispatchers.Main).launch {
                ApiUtils.launchUrlWithHeaders(context, url)
            }
        }
        builder.setNegativeButton(
            "No"
        ) { dialog: DialogInterface, which: Int ->
            dialog.cancel()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}