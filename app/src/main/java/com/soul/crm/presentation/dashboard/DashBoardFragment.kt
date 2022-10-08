package com.soul.crm.presentation.dashboard

import android.graphics.Color
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.data.*
import com.soul.crm.databinding.FragmentDashBoardBinding
import com.soul.crm.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardFragment :
    BaseFragment<FragmentDashBoardBinding>(FragmentDashBoardBinding::inflate) {
    private val viewModel: DashBoardViewModel by viewModels()

    private val list = mutableListOf<PieEntry>()
    private val barList = mutableListOf<BarEntry>()
//    private val lineList = mutableListOf<>()
    private val colors =
        mutableListOf(Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.RED)

    override fun onViewCreate() {
        initChart()
//        initWebView()
    }

//    private fun initWebView(){
//        binding.web.webViewClient = WebViewClient()
//        binding.web.loadUrl("https://storied-flan-90204f.netlify.app")
//
//        val webViewSettings = binding.web.settings
//        webViewSettings.javaScriptEnabled = true
//        webViewSettings.domStorageEnabled = true;
//    }

    private fun initChart() {
        for (i in 5 until 10) {
            list.add(PieEntry((i * (100 until 500).random()).toFloat(), "201$i"))
        }

        for (i in 5 until 20) {
            barList.add(BarEntry((i*2).toFloat(), (200 until 1000).random().toFloat()))
        }

        for (i in 5 until 20) {
            barList.add(BarEntry((i*2).toFloat(), (200 until 1000).random().toFloat()))
        }

        val pieDataSet = PieDataSet(list, "Years")
        pieDataSet.colors = colors
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTextSize = 16f

        val barDataSet = BarDataSet(barList, "Years")
        barDataSet.colors = colors
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f

        val pieData = PieData(pieDataSet)
        val barData = BarData(barDataSet)

        binding.pieChart.data = pieData
        binding.pieChart.description.isEnabled = false
        binding.pieChart.centerText = "Years"
        binding.pieChart.animate()

        binding.barChart.data = barData
        binding.barChart.description.isEnabled = false
        binding.barChart.description.text = "Years"
        binding.barChart.animate()
    }
}