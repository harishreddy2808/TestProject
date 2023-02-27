package com.reddy.data.sources.databases.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers")
data class DriverEntity(
    @PrimaryKey val id: Int,
    val firstName: String,
    val lastName: String
)