package com.example.locationreceiverkotlin.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserLocation::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userLocationDao(): UserLocationDao
}