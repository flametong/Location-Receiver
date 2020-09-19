package com.example.locationreceiverkotlin.mvp.presenter

import android.graphics.Color
import android.os.Message
import android.util.Log
import com.example.locationreceiverkotlin.App
import com.example.locationreceiverkotlin.BuildConfig
import com.example.locationreceiverkotlin.mvp.model.DatabaseModelImpl
import com.example.locationreceiverkotlin.mvp.view.MapsView
import com.example.locationreceiverkotlin.retrofit.DirectionsApi
import com.example.locationreceiverkotlin.retrofit.DirectionsResponses
import com.example.locationreceiverkotlin.room.AppDatabase
import com.example.locationreceiverkotlin.room.UserLocation
import com.example.locationreceiverkotlin.util.Constants
import com.example.locationreceiverkotlin.util.Util
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.maps.android.PolyUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@InjectViewState
class MapsPresenter : MvpPresenter<MapsView>() {

    companion object {
        private val TAG = MapsPresenter::class.simpleName
    }

    private val places = arrayListOf<LatLng>()

    private var mDatabaseModel = DatabaseModelImpl()

    @Inject
    lateinit var mFirebaseFirestore: FirebaseFirestore

    @Inject
    lateinit var mAuth: FirebaseAuth

    @Inject
    lateinit var mDatabase: AppDatabase

    @Inject
    lateinit var mDirectionsApi: DirectionsApi

    init {
        App.appComponent.inject(this)
    }

    fun updateMap() {
        viewState.clearMap()
        GlobalScope.launch {
            mDatabaseModel.clearDatabase()
        }
        places.clear()
        mFirebaseFirestore.collection(Constants.LOCATIONS)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.d(TAG, "Listen failed")
                    return@addSnapshotListener
                }

                for (dc in value!!.documentChanges) {

                    when (dc.type) {
                        DocumentChange.Type.ADDED ->
                            if (dc.document.data[Constants.USER_EMAIL]
                                == mAuth.currentUser?.email
                            ) {
                                val millisOfCurrentDate = Util.getMillisOfCurrentDate()
                                val timeInMillis =
                                    dc.document.data[Constants.TIME_IN_MILLIS] as Long
                                val latitude = dc.document.data[Constants.LATITUDE] as Double
                                val longitude = dc.document.data[Constants.LONGITUDE] as Double

                                if (timeInMillis >= millisOfCurrentDate
                                    && timeInMillis < millisOfCurrentDate + Constants.FULL_DAY_MILLIS
                                ) {

                                    places.add(LatLng(latitude, longitude))

                                    if (places.size > 1) {
                                        val origin = places[0]
                                        val destination = places[places.size - 1]

                                        val markerOrigin = MarkerOptions()
                                            .position(origin)
                                            .title(Constants.ORIGIN)

                                        val markerDestination = MarkerOptions()
                                            .position(destination)
                                            .title(Constants.DESTINATION)

                                        // Set markers in all points
                                        viewState.addMarkerToMap(markerOrigin)
                                        viewState.addMarkerToMap(markerDestination)
                                        viewState.moveMapCamera(destination)

                                        val fromOrigin = "${origin.latitude},${origin.longitude}"
                                        val toDestination =
                                            "${destination.latitude},${destination.longitude}"

                                        mDirectionsApi.getDirection(
                                            fromOrigin,
                                            toDestination,
                                            Constants.MODE_WALKING,
                                            Constants.DIRECTIONS_API_KEY
                                        ).enqueue(object : Callback<DirectionsResponses> {
                                            override fun onResponse(
                                                call: Call<DirectionsResponses>,
                                                response: Response<DirectionsResponses>
                                            ) {
                                                Log.d(TAG, "response: ${response.message()}")

                                                if (response.body() != null) {

                                                    val shape = response.body()?.getRoutes()?.get(0)
                                                        ?.getOverviewPolyline()?.getPoints()

                                                    val polyline = PolylineOptions()
                                                        .addAll(PolyUtil.decode(shape))
                                                        .width(Constants.POLYLINE_WIDTH)
                                                        .color(Color.RED)

                                                    viewState.drawLine(polylineOptions = polyline)
                                                }
                                            }

                                            override fun onFailure(
                                                call: Call<DirectionsResponses>,
                                                t: Throwable
                                            ) {
                                                Log.d(TAG, "error: ${t.localizedMessage}")
                                            }
                                        })
                                    }
                                }
                                // Insert location to db
                                GlobalScope.launch {
                                    mDatabaseModel.insertToDatabase(
                                        latitude,
                                        longitude,
                                        timeInMillis
                                    )
                                }
                            }
                        DocumentChange.Type.MODIFIED -> Log.d(
                            TAG, "Modified Msg: " +
                                    "${dc.document.toObject(Message::class.java)}"
                        )

                        DocumentChange.Type.REMOVED -> Log.d(
                            TAG, "Removed Msg: " +
                                    "${dc.document.toObject(Message::class.java)}"
                        )
                    }
                }
            }
    }

    fun updateMapWithCalendarData(calendarMillis: Long) {
        viewState.clearMap()
        places.clear()
        val channel = Channel<List<UserLocation>>()

        GlobalScope.launch {
            channel.send(mDatabaseModel.getLocationList())
        }

        GlobalScope.launch(Dispatchers.Main) {
            val locations = channel.receive()

            for (userLocation in locations) {
                val timeInMillis = userLocation.timeInMillis

                // Check for required day (calendarMillis + Constants.FULL_DAY_MILLIS = one full day)
                if (timeInMillis >= calendarMillis
                    && timeInMillis < calendarMillis + Constants.FULL_DAY_MILLIS
                ) {
                    val latitude = userLocation.latitude
                    val longitude = userLocation.longitude

                    places.add(LatLng(latitude!!, longitude!!))

                    if (places.size > 1) {
                        val origin = places[0]
                        val destination = places[places.size - 1]

                        val markerOrigin = MarkerOptions()
                            .position(origin)
                            .title(Constants.ORIGIN)

                        val markerDestination = MarkerOptions()
                            .position(destination)
                            .title(Constants.DESTINATION)

                        // Set markers in all points
                        viewState.addMarkerToMap(markerOrigin)
                        viewState.addMarkerToMap(markerDestination)
                        viewState.moveMapCamera(destination)

                        val fromOrigin = "${origin.latitude},${origin.longitude}"
                        val toDestination =
                            "${destination.latitude},${destination.longitude}"

                        mDirectionsApi.getDirection(
                            fromOrigin,
                            toDestination,
                            Constants.MODE_WALKING,
                            Constants.DIRECTIONS_API_KEY
                        ).enqueue(object : Callback<DirectionsResponses> {
                            override fun onResponse(
                                call: Call<DirectionsResponses>,
                                response: Response<DirectionsResponses>?
                            ) {
                                Log.d(TAG, "response: ${response?.message()}")

                                val shape = response?.body()?.getRoutes()?.get(0)
                                    ?.getOverviewPolyline()?.getPoints()

                                val polyline = PolylineOptions()
                                    .addAll(PolyUtil.decode(shape))
                                    .width(Constants.POLYLINE_WIDTH)
                                    .color(Color.RED)

                                viewState.drawLine(polylineOptions = polyline)
                            }

                            override fun onFailure(
                                call: Call<DirectionsResponses>,
                                t: Throwable
                            ) {
                                Log.d(TAG, "error: ${t.localizedMessage}")
                            }

                        })
                    }
                }
            }
        }
    }
}