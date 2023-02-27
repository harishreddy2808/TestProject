package com.reddy.data.mapper

import com.reddy.data.sources.api.model.Driver
import com.reddy.data.sources.api.model.Route
import com.reddy.data.sources.databases.entity.DriverEntity
import com.reddy.data.sources.databases.entity.RouteEntity

fun Route.toRouteEntity(): RouteEntity {
    return RouteEntity(
        id = this.id,
        routeType = this.type,
        name = this.name
    )
}

fun RouteEntity.toRoute(): Route {
    return Route(
        id = this.id,
        type = this.routeType,
        name = this.name
    )
}

fun Driver.toEntity(): DriverEntity {
    val nameParts = this.name.split(" ")
    val firstName = nameParts.firstOrNull() ?: ""
    val lastName = nameParts.lastOrNull() ?: ""
    return DriverEntity(this.id.toInt(), firstName, lastName)
}

fun DriverEntity.toDriver(): Driver {
    val name = "${this.firstName} ${this.lastName}"
    return Driver(this.id.toString(), name)
}