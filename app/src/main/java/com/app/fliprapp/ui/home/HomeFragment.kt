package com.app.fliprapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.fliprapp.R
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_content.*


class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener{

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        graph.apply{
            setBackgroundColor(ContextCompat.getColor(context,R.color.black))
            setDrawGridBackground(false)
            setTouchEnabled(false)
            isAutoScaleMinMaxEnabled = true
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            axisLeft.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            legend.isEnabled = false
            description.isEnabled = false
            axisLeft.textColor = ContextCompat.getColor(requireContext(),R.color.defaultText)
        }
        homeViewModel.dataModel.observe(viewLifecycleOwner, Observer {
            stock_data.text = it.close
        })
        homeViewModel.graphData.observe(viewLifecycleOwner, Observer {
            val x = LineDataSet(it,"")
            x.apply {
                setDrawFilled(true)
                setDrawCircles(false)
                lineWidth = 2f
                fillColor = ContextCompat.getColor(requireContext(),R.color.graphFill)
                setColor(ContextCompat.getColor(requireContext(),R.color.red))
                setDrawValues(false)
            }
            graph.data = LineData(x)
            graph.invalidate()
            Log.d("MainOK","okokoko")
        })
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.stock_array,
            android.R.layout.simple_spinner_item
        ).also { it ->
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_stock.adapter = it
        }
        homeViewModel.getDataBase()
        homeViewModel.setGraphData()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }
}