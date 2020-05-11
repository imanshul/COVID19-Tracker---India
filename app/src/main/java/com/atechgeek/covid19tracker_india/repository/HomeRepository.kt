package com.atechgeek.covid19tracker_india.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.atechgeek.covid19tracker_india.models.AllDataResponseModel
import com.atechgeek.covid19tracker_india.models.StateWiseResponseModel
import com.atechgeek.covid19tracker_india.retrofit.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Anshul Thakur on 21/3/20.
 */
class HomeRepository {
    val stateWiseCovidLiveData = MutableLiveData<StateWiseResponseModel>()
    val stateCovidErrorLiveData = MutableLiveData<String>()
    fun getStateWiseCovidData() {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getStateData()

                if (response.isSuccessful) {
                    //Do something with response e.g show to the UI.
                    stateWiseCovidLiveData.postValue(response.body())
                    Log.d("anshul", "Success 1: ${response.body()}")
                } else {
                    stateCovidErrorLiveData.postValue(response.message())
                    Log.d("anshul", "Error 1: ${response.code()}")
                }
            } catch (e: Exception) {
                stateCovidErrorLiveData.postValue(e.message)
                Log.d("anshul", "Ooops: Something else went wrong ${e.message}")
            }
        }
    }


    val alleCovidLiveData = MutableLiveData<AllDataResponseModel>()
    val allCovidErrorLiveData = MutableLiveData<String>()
    fun getAllCovidData() {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getAllData()

                if (response.isSuccessful) {
                    //Do something with response e.g show to the UI.
                    alleCovidLiveData.postValue(response.body())
                    Log.d("anshul", "Success 1: ${response.body()}")
                } else {
                    allCovidErrorLiveData.postValue(response.message())
                    Log.d("anshul", "Error 1: ${response.code()}")
                }
            } catch (e: Exception) {
                allCovidErrorLiveData.postValue(e.message)
                Log.d("anshul", "Ooops: Something else went wrong ${e.message}")
            }
        }
    }
}