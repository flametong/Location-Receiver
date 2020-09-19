package com.example.locationreceiverkotlin.room

import androidx.room.*

@Dao
interface UserLocationDao {
    @Query("SELECT * FROM UserLocation")
    suspend fun getAll(): List<UserLocation>

    @Query("SELECT * FROM UserLocation WHERE timeInMillis IN (:timeInMillisList)")
    suspend fun loadAllByIds(timeInMillisList: LongArray): List<UserLocation>

    @Query("SELECT * FROM UserLocation ORDER BY timeInMillis")
    suspend fun getSortedListByTimeInMillis(): List<UserLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userLocation: UserLocation)

    @Update
    suspend fun update(userLocation: UserLocation)

    @Delete
    suspend fun delete(userLocation: UserLocation)
}