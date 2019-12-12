package com.ishiki.mizuwodrinkwater.fragments

import android.app.TimePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ishiki.mizuwodrinkwater.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_reminders.*
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


class RemindersFragment : Fragment() {

    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.glassesAddButton?.hide()

        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preferences_file), 0
        )

        if (sharedPreferences != null) {

        } else {

        }

        reminders_back_arrow_left.setOnClickListener {
            activity?.glassesAddButton?.show()
            val homeFragment = HomeFragment()
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, homeFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        reminders_on_off.setOnClickListener {
            when (reminders_on_off.isChecked) {
                true -> {
                    reminders_when_container.visibility = View.VISIBLE
                    reminders_time_container.visibility = View.VISIBLE
                    reminders_how_often_container.visibility = View.VISIBLE

                }
                false -> {
                    reminders_when_container.visibility = View.INVISIBLE
                    reminders_time_container.visibility = View.INVISIBLE
                    reminders_how_often_container.visibility = View.INVISIBLE
                }
            }
        }

        reminders_time_from_select.text = "8:00 AM"
        reminders_time_to_select.text = "4:00 PM"

        val timeFrom = LocalTime.parse(reminders_time_from_select.text,
            DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
        val timeTo = LocalTime.parse(reminders_time_to_select.text,
            DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))

        val timeFormat = SimpleDateFormat("hh:mm")

        reminders_time_from_select.setOnClickListener {
            // Create time picker
            val hour = timeFrom.hour
            val minute = timeFrom.minute
            val timePicker = TimePickerDialog(activity,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    // If we change the time in time picker we'll get the callback in OnTimeSetListener
                    // Do something with the time chosen by the user
                    val selectedTime = Calendar.getInstance()
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set(Calendar.MINUTE, minute)

                    reminders_time_from_select.text = timeFormat.format(selectedTime.time)

                }, hour, minute, true)

            timePicker.show()
        }

        reminders_time_to_select.setOnClickListener {
            val hour = timeTo.hour
            val minute = timeTo.minute
            val timePicker = TimePickerDialog(activity,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    // If we change the time in time picker we'll get the callback in OnTimeSetListener
                    // Do something with the time chosen by the user
                    val selectedTime = Calendar.getInstance()
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set(Calendar.MINUTE, minute)

                    reminders_time_from_select.text = timeFormat.format(selectedTime.time)

                }, hour, minute, true)

            timePicker.show()
        }

    }
}
