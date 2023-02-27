package com.reddy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reddy.data.repositories.DriverRepository
import com.reddy.data.sources.databases.entity.DriverEntity
import com.reddy.presentation.helper.SingleLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class SortOrder {
    FIRST_NAME,
    LAST_NAME
}

sealed class DriverUiState {
    object Loading : DriverUiState()
    class Success(val drivers: List<DriverEntity>) : DriverUiState()
    class Error(val message: String) : DriverUiState()
}

class DriverViewModel(private val repository: DriverRepository) : ViewModel() {

    private var sortOrder = SortOrder.FIRST_NAME

    private val _uiState = MutableLiveData<DriverUiState>()
    val uiState: LiveData<DriverUiState>
        get() = _uiState

    private val _navigateToDriverRoute = SingleLiveData<Int>()
    val navigateToDriverRoute: LiveData<Int>
        get() = _navigateToDriverRoute

    fun getDrivers(sortOrder: SortOrder) {
        sortOrder.also { this.sortOrder = it }
        _uiState.postValue(DriverUiState.Loading)
        viewModelScope.launch {
            try {
                val drivers: List<DriverEntity> = withContext(Dispatchers.IO) {
                    when (sortOrder) {
                        SortOrder.FIRST_NAME -> repository.getDriversSortedByFirstName()
                        SortOrder.LAST_NAME -> repository.getDriversSortedByLastName()
                    }
                }
                _uiState.postValue(DriverUiState.Success(drivers))
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.postValue(DriverUiState.Error("Failed to load drivers."))
            }
        }
    }

    fun onSortOrderChanged(sortOrder: SortOrder) {
        if (sortOrder != this.sortOrder) {
            getDrivers(sortOrder)
        }
    }
}
