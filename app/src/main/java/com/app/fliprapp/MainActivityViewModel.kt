package com.app.fliprapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    val dataModel = MutableLiveData<DataModel>()

    fun getDatabase(){
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
}