package com.example.locationreceiverkotlin.activity

import android.os.Bundle
import com.example.locationreceiverkotlin.R
import com.example.locationreceiverkotlin.databinding.ActivityMapsBinding
import com.example.locationreceiverkotlin.dialog.CalendarDialog
import com.example.locationreceiverkotlin.interfaces.CalendarDialogListener
import com.example.locationreceiverkotlin.mvp.presenter.MapsPresenter
import com.example.locationreceiverkotlin.mvp.view.MapsView
import com.example.locationreceiverkotlin.util.Constants
import com.example.locationreceiverkotlin.util.Variables
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MapsActivity : MvpAppCompatActivity(), OnMapReadyCallback, MapsView, CalendarDialogListener {

    private var mMap: GoogleMap? = null
    private lateinit var binding: ActivityMapsBinding

    @InjectPresenter
    lateinit var mPresenter: MapsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Variables.isMapActive = true

        binding.btnOpenCalendar.setOnClickListener {
            CalendarDialog().show(supportFragmentManager, Constants.OPEN_DIALOG)
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mPresenter.updateMap()
    }

    // Callback value from CalendarDialog
    override fun passTimeInMillis(timeInMillis: Long) {
        mPresenter.updateMapWithCalendarData(calendarMillis = timeInMillis)
    }

    override fun clearMap() {
        mMap?.clear()
    }

    // Add marker
    override fun addMarkerToMap(marker: MarkerOptions) {
        mMap?.addMarker(marker)
    }

    // Move camera to required coordinates
    override fun moveMapCamera(coordinates: LatLng) {
        mMap?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                coordinates,
                Constants.ZOOM_LEVEL
            )
        )
    }

    // Draw polyline
    override fun drawLine(polylineOptions: PolylineOptions) {
        mMap?.addPolyline(polylineOptions)
    }
}