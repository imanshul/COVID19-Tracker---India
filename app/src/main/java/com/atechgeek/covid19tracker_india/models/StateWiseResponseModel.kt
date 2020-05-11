package com.atechgeek.covid19tracker_india.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Anshul Thakur on 21/3/20.
 */

data class StateWiseResponseModel(
    @SerializedName("data")
    val data: StateListData = StateListData(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("statusCode")
    val statusCode: Int = 0
)

data class StateListData(
    @SerializedName("stateData")
    val stateData: List<StateData> = listOf()
)

data class StateData(
    @SerializedName("active_cases")
    val activeCases: Int,
    @SerializedName("confirmed_cases")
    val confirmedCases: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("recovered_cases")
    val recoveredCases: Int,
    @SerializedName("state_code")
    val stateCode: String = "",
    @SerializedName("total")
    val total: Int
):Serializable