package com.example.locationreceiverkotlin.util

import android.Manifest

object Constants {
    const val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    const val DIALOG_TAG = "REQUEST_LOCATION_DIALOG"
    const val OPEN_DIALOG = "Open dialog"
    const val LOCATIONS = "locations"
    const val DATABASE_NAME = "UserLocation.db"
    const val TABLE_NAME = "UserLocation"
    const val USER_EMAIL = "userEmail"
    const val LATITUDE = "latitude"
    const val LONGITUDE = "longitude"
    const val TIME_IN_MILLIS = "timeInMillis"
    const val POINTS = "points"
    const val OVERVIEW_POLYLINE = "overview_polyline"
    const val ROUTES = "routes"
    const val DIRECTIONS_API_LINK = "maps/api/directions/json"
    const val ORIGIN = "origin"
    const val DESTINATION = "destination"
    const val MODE = "mode"
    const val KEY = "key"
    const val MODE_WALKING = "walking"
    const val ZOOM_LEVEL = 11.6F
    const val POLYLINE_WIDTH = 8F
    const val FULL_DAY_MILLIS = 86400000L
    const val DIRECTIONS_API_KEY = "YOUR_API_KEY"
}