package com.reddy.data.di

import com.reddy.data.repositories.DriverRepository
import com.reddy.data.repositories.RouteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        DriverRepository(
            apiService = get(), driverDao = get(), routeDao = get()
        )
    }

    single {
        RouteRepository(
            routeDao = get()
        )
    }
}
