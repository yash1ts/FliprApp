package com.app.fliprapp.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.fliprapp.ApplicationClass
import com.app.fliprapp.DataModel
import com.app.fliprapp.GraphModel
import com.github.mikephil.charting.data.Entry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val graphData = MutableLiveData<List<Entry>>()
    var dataModel = MutableLiveData<DataModel>()
    init {
        graphData.postValue(emptyList())
        dataModel.postValue(DataModel())
    }
    fun getDataBase() {
        getApplication<ApplicationClass>().repository.getData()
            ?.enqueue(object : Callback<DataModel> {
                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                }

                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                    if (response.isSuccessful) {
                        dataModel.postValue(response.body() ?: DataModel())
                    }
                }

            })
    }

    fun setGraphData() {
        getApplication<ApplicationClass>().repository.getGraph()
            ?.enqueue(object : Callback<GraphModel> {
                override fun onFailure(call: Call<GraphModel>, t: Throwable) {
                }

                override fun onResponse(call: Call<GraphModel>, response: Response<GraphModel>) {
                    val ins = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
                    if (response.isSuccessful) {
                        val a = mutableListOf<Entry>()
//                        response.body()?.result?.forEach {
//                            val date = ins.parse(it.date) ?: Date()
////                            a.add(Entry(it.close, date.time.toFloat()))
//                            Log.d("MAINOK",date.time.toFloat().toString()+","+it.close)
//                        }
                        val d = response.body()?.result ?: emptyList()
                        for(i in d.indices){
                            a.add(Entry(i.toFloat(),d[d.size - i-1].close/100))
                        }
                        Log.d("MAINOK",a.toString())
                        graphData.postValue(a.toList())
                    }
                }

            })
//        val x = listOf(Entry(1f,50f), Entry(2f,100f),Entry(3f,150f))
//        graphData.value = x
    }
}