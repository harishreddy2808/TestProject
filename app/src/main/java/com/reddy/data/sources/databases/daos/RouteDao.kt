package com.reddy.data.sources.databases.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reddy.data.sources.databases.entity.RouteEntity

@Dao
interface RouteDao {
    @Query("SELECT * FROM routes WHERE id = :id AND routeType = :type")
    fun getByDriverAndType(id: Int, type: String): RouteEntity?

    @Query("SELECT * FROM routes WHERE id = :id")
    fun getDriverById(id: Int): RouteEntity?

    @Query("SELECT * FROM routes WHERE routeType = :routeType")
    fun getDriverByRouteType(routeType: String): RouteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(routes: List<RouteEntity>)
}