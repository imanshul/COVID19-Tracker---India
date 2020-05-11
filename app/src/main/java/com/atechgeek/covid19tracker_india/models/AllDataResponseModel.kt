package com.atechgeek.covid19tracker_india.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Anshul Thakur on 21/3/20.
 */

data class AllDataResponseModel(
    @SerializedName("data")
    val data: AllListData = AllListData(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("statusCode")
    val statusCode: Int = 0
)

data class AllListData(
    @SerializedName("allData")
    val allData: List<AllData> = listOf()
)

data class AllData(
    @SerializedName("active")
    val active: Int,
    @SerializedName("cases_mohfw")
    val casesMohfw: Int,
    @SerializedName("confirmed")
    val confirmed: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("states_affected")
    val statesAffected: Int,
    @SerializedName("timestamp")
    val timestamp: String = "",
    @SerializedName("total_test_done")
    val totalTestDone: Int,
    @SerializedName("treatment_ongoing")
    val treatmentOngoing: Int
):Serializable