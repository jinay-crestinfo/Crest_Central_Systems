package com.crestinfosystems_jinay.crestcentralsystems.adapter

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crestinfosystems_jinay.crestcentralsystems.databinding.HrmsPoliciesTileBinding
import com.crestinfosystems_jinay.crestcentralsystems.model.Policies

class HRMSPoliciesAdapter(
    private var items: List<Policies>,
    var context: Context,

    ) :
    RecyclerView.Adapter<HRMSPoliciesAdapter.ViewHolder>() {
    lateinit var binding:
            HrmsPoliciesTileBinding

    inner class ViewHolder(var binding: HrmsPoliciesTileBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding =
            HrmsPoliciesTileBinding.inflate(
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

        }
        with(holder)
        {
            with(items[position]) {
                binding.title.text = items[position].title
                binding.details.text = Html.fromHtml(items[position].details)
                binding.titleLayout.setOnClickListener {
                    if (binding.details.visibility == View.VISIBLE) {
                        binding.details.visibility = View.GONE
                        binding.titleLayout.setBackgroundColor(Color.WHITE)
                        binding.title.setTextColor(Color.BLACK)
                    } else {
                        binding.details.visibility = View.VISIBLE
                        binding.titleLayout.setBackgroundColor(Color.parseColor("#7CB900"))
                        binding.title.setTextColor(Color.WHITE)
                    }
                }

            }
        }
    }
}