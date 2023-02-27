package com.reddy.data.sources.databases.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes")
data class RouteEntity(
    @PrimaryKey val id: Int,
    val routeType: String,
    val name: String
)
