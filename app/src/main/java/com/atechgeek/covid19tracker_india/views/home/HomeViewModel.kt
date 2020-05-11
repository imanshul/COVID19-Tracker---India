package com.atechgeek.covid19tracker_india.views.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atechgeek.covid19tracker_india.models.AllDataResponseModel
import com.atechgeek.covid19tracker_india.models.StateWiseResponseModel
import com.atechgeek.covid19tracker_india.repository.HomeRepository

/**
 * Created by Anshul Thakur on 21/3/20.
 */

class HomeViewModel : ViewModel() {

    private var repository: HomeRepository? = null

    init {
        repository = HomeRepository()
    }

    fun getSatateWiseData() {
        repository?.getStateWiseCovidData()
    }

    fun getStateCovidLiveData(): MutableLiveData<StateWiseResponseModel>? {
        return repository?.stateWiseCovidLiveData
    }

    fun getStateCovidErrorLiveData(): MutableLiveData<String>? {
        return repository?.stateCovidErrorLiveData
    }


    fun getAllData() {
        repository?.getAllCovidData()
    }

    fun getAllCovidLiveData(): MutableLiveData<AllDataResponseModel>? {
        return repository?.alleCovidLiveData
    }

    fun getAllCovidErrorLiveData(): MutableLiveData<String>? {
        return repository?.allCovidErrorLiveData
    }
}