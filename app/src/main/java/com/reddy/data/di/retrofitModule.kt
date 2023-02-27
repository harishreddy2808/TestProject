package com.reddy.data.di

import com.google.gson.GsonBuilder
import com.reddy.data.sources.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        Retrofit.Builder().baseUrl("https://da7749d7-3e59-4c5b-a532-40aa696b5371.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}