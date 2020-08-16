package com.app.fliprapp

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class Formatter : ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(Date(value.toLong()))
    }
}