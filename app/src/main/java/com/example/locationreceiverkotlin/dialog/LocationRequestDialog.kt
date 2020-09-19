package com.example.locationreceiverkotlin.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.DialogFragment
import com.example.locationreceiverkotlin.R

class LocationRequestDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .apply {
                setMessage(R.string.gps_enable)
                setMessage(R.string.please_turn_on_gps)
                setPositiveButton(R.string.ok) { _, _ ->
                    dismiss()
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
            }.create()
    }
}