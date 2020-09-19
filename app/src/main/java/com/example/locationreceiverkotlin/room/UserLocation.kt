package com.example.locationreceiverkotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.locationreceiverkotlin.util.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class UserLocation(
    @ColumnInfo(name = Constants.USER_EMAIL) val userEmail: String?,
    @ColumnInfo(name = Constants.LATITUDE) val latitude: Double?,
    @ColumnInfo(name = Constants.LONGITUDE) val longitude: Double?,
    @PrimaryKey
    @ColumnInfo(name = Constants.TIME_IN_MILLIS) val timeInMillis: Long
)