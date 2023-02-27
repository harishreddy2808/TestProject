package com.reddy.data.repositories

import com.reddy.data.sources.databases.daos.RouteDao
import com.reddy.data.sources.databases.entity.RouteEntity

class RouteRepository(private val routeDao: RouteDao) {

    suspend fun getRouteForDriver(driverId: Int): RouteEntity? {
        val route: RouteEntity?
        val driver = routeDao.getDriverById(driverId)
        route = if (driverId == driver?.id) {
            // case a: driver id is the same as the route id
            routeDao.getByDriverAndType(driverId, driver.routeType)
        } else if (driverId % 2 == 0) {
            // case b: driver id is divisible by 2
            routeDao.getDriverByRouteType("R")
        } else if (driverId % 5 == 0) {
            // case c: driver id is divisible by 5
            routeDao.getDriverByRouteType("C")
        } else {
            // case d: driver id doesn't meet any of the above conditions
            routeDao.getDriverByRouteType("I")
        }
        return route
    }
}
