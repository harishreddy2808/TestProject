package com.reddy.data.sources.api

import com.reddy.data.sources.api.model.DataResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/data")
    suspend fun getDriversWithRoutes(): DataResponse
}