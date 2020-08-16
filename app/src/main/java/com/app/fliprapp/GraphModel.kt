package com.app.fliprapp

data class GraphModel(
    val result: List<GraphData>
)

data class GraphData(
    val close: Float,
    val date: String
)