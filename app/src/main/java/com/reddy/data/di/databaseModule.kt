package com.reddy.data.di

import androidx.room.Room
import com.reddy.data.sources.databases.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(), AppDatabase::class.java, "my-app-database"
        ).build()
    }

    single {
        get<AppDatabase>().driverDao()
    }

    single {
        get<AppDatabase>().routeDao()
    }
}

