package com.example.locationreceiverkotlin.mvp.model

import android.util.Log
import com.example.locationreceiverkotlin.App
import com.example.locationreceiverkotlin.room.AppDatabase
import com.example.locationreceiverkotlin.room.UserLocation
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RoomModel {

    companion object {
        private val TAG = RoomModel::class.java.simpleName
    }

    @Inject
    lateinit var mDatabase: AppDatabase

    @Inject
    lateinit var mAuth: FirebaseAuth

    init {
        App.appComponent.inject(this)
    }

    suspend fun insertToDatabase(latitude: Double, longitude: Double, timeInMillis: Long) {
        val userLocation = UserLocation(
            mAuth.currentUser?.email,
            latitude,
            longitude,
            timeInMillis
        )

        mDatabase.userLocationDao().insert(userLocation)
        Log.d(TAG, "Insert completed")
    }

    suspend fun getLocationList(): List<UserLocation> =
        mDatabase.userLocationDao().getSortedListByTimeInMillis()
}