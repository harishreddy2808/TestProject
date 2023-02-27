package com.reddy.data.repositories

import com.reddy.data.mapper.toEntity
import com.reddy.data.mapper.toRouteEntity
import com.reddy.data.sources.api.ApiService
import com.reddy.data.sources.databases.daos.DriverDao
import com.reddy.data.sources.databases.daos.RouteDao
import com.reddy.data.sources.databases.entity.DriverEntity

class DriverRepository(
    private val apiService: ApiService,
    private val driverDao: DriverDao,
    private val routeDao: RouteDao,
) {

    suspend fun getDriversSortedByFirstName(): List<DriverEntity> {
        val drivers = driverDao.getAllSortedByFirstName()
        fetchAndInsetToDb(drivers)
        return driverDao.getAllSortedByFirstName()
    }

    suspend fun getDriversSortedByLastName(): List<DriverEntity> {
        val drivers = driverDao.getAllSortedByLastName()
        fetchAndInsetToDb(drivers)
        return driverDao.getAllSortedByLastName()
    }

    private suspend fun fetchAndInsetToDb(drivers: List<DriverEntity>) {
        if (drivers.isEmpty()) {
            val response = apiService.getDriversWithRoutes()
            driverDao.insertAll(response.drivers.map { it.toEntity() })
            routeDao.insertAll(response.routes.map { it.toRouteEntity() })
        }
    }
}