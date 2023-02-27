package com.reddy.data.sources.api.model

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("drivers")
    val drivers: List<Driver>,
    @SerializedName("routes")
    val routes: List<Route>
)