package com.ashu.mftracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.ashu.mftracker.R
import com.ashu.mftracker.data.response.MutualFundNav
import com.ashu.mftracker.databinding.FragmentNavExploreBinding
import com.ashu.mftracker.global.clickWithDebounce
import com.ashu.mftracker.global.network.Status
import com.ashu.mftracker.global.to2Digits
import com.ashu.mftracker.global.withArgs
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import io.github.farshidroohi.ChartEntity
import java.math.RoundingMode
import java.text.DecimalFormat

class NavExplorerFragment: Fragment() {

    private var _binding: FragmentNavExploreBinding? = null

    private val binding get() = _binding!!

    companion object {
        private const val NAV_LIST = "scheme_id"
        private const val SCHEME_NAME = "scheme_name"
        fun newInstance(mutualFundNav: List<MutualFundNav>?, schemeName: String?) = NavExplorerFragment().apply {
            arguments = bundleOf(
                NAV_LIST to mutualFundNav,
                SCHEME_NAME to schemeName
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavExploreBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val data = arguments?.getParcelableArrayList<MutualFundNav>(NAV_LIST)
        val schemeName = arguments?.getString(SCHEME_NAME)
        val dates = mutableListOf<String>()
        val navList = mutableListOf<String>()
        val navListfloat = mutableListOf<Float?>()
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.UP

        data?.let {
            for (d in data) {
                dates.add(d.date)
                navList.add(d.nav.toFloat().to2Digits().toString())
                navListfloat.add(d.nav.toFloatOrNull())
            }
        }

        binding.tvMfName.text = schemeName

        setUpLineChart(dates, navList)

        setData(navListfloat)

        binding.fbZoomReset.clickWithDebounce {
            binding.lcLine.fitScreen()
        }

        binding.fbZoomIn.clickWithDebounce {
            binding.lcLine.zoomOut()
        }

        binding.fbZoomOut.clickWithDebounce {
            binding.lcLine.zoomIn()
        }

        return root
    }

    private fun setUpLineChart(dates: List<String>, navList: List<String>) {
        with(binding.lcLine) {
            animateX(1200, Easing.EaseInSine)
            description.isEnabled = false

            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1F
            xAxis.valueFormatter = MyAxisFormatter(dates)

            axisRight.isEnabled = false
            extraRightOffset = 30f

            axisLeft.valueFormatter = YAxisFormatter(navList)

            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.textSize = 15F
            legend.form = Legend.LegendForm.LINE
            setTouchEnabled(true)
            setPinchZoom(true)
        }
    }

    private fun setData(navList: List<Float?>) {
        val values = mutableListOf<Entry>()
        for ((ind, element) in navList.withIndex()) {
            element?.let { Entry(ind.toFloat(), it) }?.let { values.add(it) }
        }

        val setLine = LineDataSet(values, "")

        val dataSet = mutableListOf<ILineDataSet>()
        dataSet.add(setLine)

        val data = LineData(dataSet)
        binding.lcLine.data = data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class MyAxisFormatter(private val dates: List<String>) : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String? {

            val index = value.toInt()
            return if (index < dates.size) {
                dates[index]
            } else {
                null
            }
        }
    }

    inner class YAxisFormatter(private val navList: List<String>): ValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
            val index = value.toInt()
            return if (index < navList.size) {
                navList[index]
            } else {
                null
            }
        }
    }
}