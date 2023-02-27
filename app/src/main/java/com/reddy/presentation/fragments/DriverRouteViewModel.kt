package com.reddy.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reddy.data.repositories.RouteRepository
import com.reddy.data.sources.databases.entity.RouteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class DriverRouteState {
    object Loading : DriverRouteState()
    data class Success(val route: RouteEntity) : DriverRouteState()
    data class Error(val message: String) : DriverRouteState()
}

class DriverRouteViewModel(private val routeRepository: RouteRepository) : ViewModel() {

    private val _driverRouteState = MutableLiveData<DriverRouteState>()
    val driverRouteState: LiveData<DriverRouteState>
        get() = _driverRouteState

    fun getRouteForDriver(driverId: String) {
        _driverRouteState.value = DriverRouteState.Loading
        viewModelScope.launch {
            try {
                val route =
                    withContext(Dispatchers.IO) {
                        routeRepository.getRouteForDriver(driverId.toInt())
                    }
                if (route != null) {
                    _driverRouteState.value = DriverRouteState.Success(route)
                } else {
                    _driverRouteState.value = DriverRouteState.Error("Route not found")
                }
            } catch (e: Exception) {
                _driverRouteState.value = DriverRouteState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
