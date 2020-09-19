package com.example.locationreceiverkotlin.mvp.model

import com.example.locationreceiverkotlin.room.UserLocation

interface DatabaseModel {
    suspend fun insertToDatabase(
        latitude: Double,
        longitude: Double,
        timeInMillis: Long
    )

    suspend fun clearDatabase()
    suspend fun getLocationList(): List<UserLocation>
}