package com.atechgeek.covid19tracker_india.retrofit

import com.atechgeek.covid19tracker_india.models.AllDataResponseModel
import com.atechgeek.covid19tracker_india.models.StateWiseResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    /*
    * To get StateWise Covid19 Data
    * */

    @GET("data/state_data.php")
    suspend fun getStateData(): Response<StateWiseResponseModel>

    @GET("data/all_data.php")
    suspend fun getAllData(): Response<AllDataResponseModel>
}