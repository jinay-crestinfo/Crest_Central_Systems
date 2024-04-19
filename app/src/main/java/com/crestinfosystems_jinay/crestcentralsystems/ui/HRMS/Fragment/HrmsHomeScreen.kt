package com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.crestinfosystems_jinay.crestcentralsystems.databinding.FragmentHrmsHomeScreenBinding


class HrmsHomeScreen : Fragment() {
    lateinit var binding: FragmentHrmsHomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHrmsHomeScreenBinding.inflate(layoutInflater)
//        val entries = listOf(
//            PieEntry(187f, "Developer"),
//            PieEntry(4f, "Designer"),
//            PieEntry(14f, "QA"),
//            PieEntry(10f, "Marketing and Sales"),
//            PieEntry(5f, "HR"),
//            PieEntry(4f, "Management"),
//            PieEntry(2f, "Account")
//        )
//        val dataSet = PieDataSet(entries, "Election Results")
//        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
//        dataSet.valueTextSize = 15f
//        binding.pieChart.setDrawEntryLabels(false)
//        binding.pieChart.setUsePercentValues(true)
//        binding.pieChart.getDescription().setEnabled(true)
//        binding.pieChart.description.text = "Total User of HRMS"
//        binding.pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
//
//        // on below line we are setting drag for our pie chart
//        binding.pieChart.setDragDecelerationFrictionCoef(0.95f)
//
//        // on below line we are setting hole
//        // and hole color for pie chart
//        binding.pieChart.setDrawHoleEnabled(true)
//        binding.pieChart.setHoleColor(Color.WHITE)
//
//        // on below line we are setting circle color and alpha
//        binding.pieChart.setTransparentCircleColor(Color.WHITE)
//        binding.pieChart.setTransparentCircleAlpha(110)
//
//        // on  below line we are setting hole radius
//        binding.pieChart.setHoleRadius(58f)
//        binding.pieChart.setTransparentCircleRadius(61f)
//
//        // on below line we are setting center text
//        binding.pieChart.setDrawCenterText(true)
//
//        // on below line we are setting
//        // rotation for our pie chart
//        binding.pieChart.setRotationAngle(0f)
//
//        // enable rotation of the pieChart by touch
//        binding.pieChart.setRotationEnabled(true)
//        binding.pieChart.setHighlightPerTapEnabled(true)
//
//        // on below line we are setting animation for our pie chart
//        binding.pieChart.animateY(1400, Easing.EaseInOutQuad)
//
//        // on below line we are disabling our legend for pie chart
//        binding.pieChart.legend.isEnabled = true
//        binding.pieChart.setEntryLabelColor(Color.BLACK)
//        binding.pieChart.setEntryLabelTextSize(15f)
//        val data = PieData(dataSet)
//        binding.pieChart.data = data
//        binding.pieChart.invalidate() // Refreshes the chart
        val pie = AnyChart.pie()

        pie.setOnClickListener(object :
            ListenersInterface.OnClickListener(arrayOf<String>("x", "value")) {
            override fun onClick(event: Event) {
            }
        })

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Developer", 187))
        data.add(ValueDataEntry("Designer", 4))
        data.add(ValueDataEntry("QA", 14))
        data.add(ValueDataEntry("Marketing and Sales", 10))
        data.add(ValueDataEntry("HR", 5))
        data.add(ValueDataEntry("Management", 4))
        data.add(ValueDataEntry("Account", 2))
        pie.data(data)

        pie.title("Active Users of HRMS")

        pie.labels().position("outside")

        pie.legend().title().enabled(true)
        pie.legend().title()
            .text("Total Users (226)")
            .padding(0.0, 0.0, 10.0, 0.0)

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)

        binding.anyChartView.setChart(pie)
        return binding.root

    }

}