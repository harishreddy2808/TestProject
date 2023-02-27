package com.reddy.data.sources.api.model

import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("name")
    val name: String
)