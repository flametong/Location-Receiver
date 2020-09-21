package com.example.locationreceiverkotlin.room

import androidx.room.*

@Dao
interface UserLocationDao {
    @Query("SELECT * FROM UserLocation ORDER BY timeInMillis")
    suspend fun getSortedListByTimeInMillis(): List<UserLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userLocation: UserLocation)
}