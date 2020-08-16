package com.app.fliprapp

import retrofit2.Call
import retrofit2.http.GET

interface Repository {
    @GET("/graph")
    fun getGraph(): Call<GraphModel>?

    @GET("/")
    fun getData(): Call<DataModel>?
}
