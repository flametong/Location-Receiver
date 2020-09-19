package com.example.locationreceiverkotlin.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.locationreceiverkotlin.R
import com.example.locationreceiverkotlin.databinding.FragmentCalendarBinding
import com.example.locationreceiverkotlin.interfaces.CalendarDialogListener
import java.lang.ClassCastException
import java.util.*

class CalendarDialog : DialogFragment() {

    private var mListener: CalendarDialogListener? = null
    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentCalendarBinding.inflate(LayoutInflater.from(context))

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = GregorianCalendar(year, month, dayOfMonth)
            mListener?.passTimeInMillis(timeInMillis = calendar.timeInMillis)
            dismiss()
        }

        return AlertDialog.Builder(activity)
            .apply {
                setView(binding.root)
                setTitle(R.string.choose_date)
            }.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as CalendarDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString() +
                        " must implement DialogWindowListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
}