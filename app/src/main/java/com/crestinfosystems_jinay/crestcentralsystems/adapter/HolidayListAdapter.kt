package com.crestinfosystems_jinay.crestcentralsystems.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crestinfosystems_jinay.crestcentralsystems.databinding.HolidayListTileBinding
import com.crestinfosystems_jinay.crestcentralsystems.model.holiday
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class HolidayListAdapter(
    private var items: List<holiday>,
    var context: Context,
) :
    RecyclerView.Adapter<HolidayListAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: HolidayListTileBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            HolidayListTileBinding.inflate(
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
                val dateTime = LocalDateTime.parse(
                    items[position].holiday_date,
                    DateTimeFormatter.ISO_DATE_TIME
                )
                binding.dateText.text = dateTime.dayOfMonth.toString()
                binding.dayName.text = dateTime.dayOfWeek.name
                binding.holidayNameText.text = items[position].holiday_reason
                binding.monthText.text =
                    dateTime.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            }
        }
    }

    fun submitList(newData: List<holiday>) {
        items = newData
        notifyDataSetChanged()
    }

}