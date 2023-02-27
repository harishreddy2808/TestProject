package com.reddy.data.sources.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reddy.data.sources.databases.entity.DriverEntity
import com.reddy.data.sources.databases.entity.RouteEntity
import com.reddy.data.sources.databases.daos.DriverDao
import com.reddy.data.sources.databases.daos.RouteDao

@Database(entities = [DriverEntity::class, RouteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun driverDao(): DriverDao
    abstract fun routeDao(): RouteDao
}