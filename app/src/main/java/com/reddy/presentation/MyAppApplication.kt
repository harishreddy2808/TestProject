package com.reddy.presentation

import android.app.Application
import com.reddy.data.di.databaseModule
import com.reddy.data.di.repositoryModule
import com.reddy.data.di.retrofitModule
import com.reddy.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyAppApplication)
            modules(databaseModule + retrofitModule + viewModelModule + viewModelModule + repositoryModule)
        }
    }
}

