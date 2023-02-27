package com.reddy.presentation.di

import com.reddy.presentation.fragments.DriverRouteViewModel
import com.reddy.presentation.viewmodel.DriverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        DriverViewModel(repository = get())
    }

    viewModel {
        DriverRouteViewModel(
            routeRepository = get()
        )
    }
}
