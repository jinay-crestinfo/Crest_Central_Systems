package com.crestinfosystems_jinay.crestcentralsystems.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crestinfosystems_jinay.crestcentralsystems.R
import com.crestinfosystems_jinay.crestcentralsystems.databinding.HrmsUsersTileBinding
import com.crestinfosystems_jinay.crestcentralsystems.model.user

class HRMSUsersListAdapter(
    private var items: List<user>,
    var context: Context,
    var onClick: (user) -> Unit
) :
    RecyclerView.Adapter<HRMSUsersListAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: HrmsUsersTileBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            HrmsUsersTileBinding.inflate(
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
            onClick(items[position])
        }
        with(holder)
        {
            with(items[position]) {
                binding.name.text = items[position].first_name + " " + items[position].last_name
                binding.designationName.text = items[position].designation_name
            }
        }
    }
}