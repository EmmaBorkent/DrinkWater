package com.ishiki.mizuwodrinkwater.fragments

import android.app.TimePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.services.FROM_TIME
import com.ishiki.mizuwodrinkwater.services.REMINDERS_ON_OFF
import com.ishiki.mizuwodrinkwater.services.REMINDERS_RADIO
import com.ishiki.mizuwodrinkwater.services.TO_TIME
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_reminders.*
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class RemindersFragment : Fragment() {

    private var sharedPreferences: SharedPreferences? = null
    private var timeFrom = "7:00 AM"
    private var timeTo   = "9:00 PM"
    private val timeFromParsed: LocalTime = LocalTime.parse(timeFrom, DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
    private val timeToParsed: LocalTime = LocalTime.parse(timeTo, DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.GERMAN)

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
            val isChecked = sharedPreferences!!.getBoolean(REMINDERS_ON_OFF, false)
            reminders_on_off.isChecked = isChecked
            setRemindersOnOff()

            val radioButton = sharedPreferences!!.getInt(REMINDERS_RADIO, 0)
            reminders_radio_group.check(radioButton)

            val fromTime = sharedPreferences!!.getString(FROM_TIME,
                "00:00")
            val toTime = sharedPreferences!!.getString(TO_TIME,
                getString(R.string.reminders_time_to_time))
            reminders_time_from_select.text = fromTime
            reminders_time_to_select.text = toTime
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
            setRemindersOnOff()
        }

        reminders_radio_group.setOnCheckedChangeListener { group, checkedId ->
            // checkedId is the RadioButton selected
            when (checkedId) {
                R.id.reminders_radio_behind -> {

                }
                R.id.reminders_radio_always -> {

                }
            }
            sharedPreferences!!.edit().putInt(REMINDERS_RADIO,
                reminders_radio_group.checkedRadioButtonId).apply()
        }

        reminders_time_from_select.setOnClickListener {
            createTimePicker(activity!!, FROM_TIME, reminders_time_from_select, timeFromParsed)
        }

        reminders_time_to_select.setOnClickListener {
            createTimePicker(activity!!, TO_TIME, reminders_time_to_select, timeToParsed)
        }

    }

    private fun setRemindersOnOff() {
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
        sharedPreferences!!.edit().putBoolean(REMINDERS_ON_OFF, reminders_on_off.isChecked).apply()
    }

    private fun createTimePicker(activity: FragmentActivity, const: String, textView: TextView, parsedTime: LocalTime) {
        // Create time picker
        val timePicker = TimePickerDialog(activity,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                // If we change the time in time picker we'll get the callback in OnTimeSetListener
                // Do something with the time chosen by the user
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)

                val formatted = timeFormat.format(selectedTime.time)
                sharedPreferences?.edit()?.putString(const, formatted)?.apply()
                textView.text = formatted

            }, parsedTime.hour, parsedTime.minute, true)

        timePicker.show()
    }
}
