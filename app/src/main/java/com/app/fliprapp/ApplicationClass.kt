package com.app.fliprapp

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://yash2000.pythonanywhere.com"
class ApplicationClass : Application() {
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        repository = retrofit.create(Repository::class.java)
    }
}
