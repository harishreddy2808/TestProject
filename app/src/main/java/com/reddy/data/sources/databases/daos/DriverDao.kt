package com.reddy.data.sources.databases.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reddy.data.sources.databases.entity.DriverEntity

@Dao
interface DriverDao {
    @Query("SELECT * FROM drivers ORDER BY firstName ASC")
    fun getAllSortedByFirstName(): List<DriverEntity>

    @Query("SELECT * FROM drivers ORDER BY lastName ASC")
    fun getAllSortedByLastName(): List<DriverEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(drivers: List<DriverEntity>)
}