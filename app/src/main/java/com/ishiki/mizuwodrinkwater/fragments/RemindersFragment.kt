package com.ishiki.mizuwodrinkwater.fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.services.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_reminders.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class RemindersFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var sharedPreferences: SharedPreferences? = null
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

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

        val timeFrom: String
        val timeTo: String

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(context!!, R.array.interval_array,
            android.R.layout.simple_spinner_item).also { arrayAdapter ->
            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            reminders_spinner.adapter = arrayAdapter
        }
        reminders_spinner.onItemSelectedListener = this

        sharedPreferences = activity?.getSharedPreferences(getString(R.string.shared_preferences_file),
            0)

        if (sharedPreferences != null) {
            val isChecked = sharedPreferences!!.getBoolean(REMINDERS_ON_OFF, false)
            reminders_on_off.isChecked = isChecked
            setRemindersOnOff()

            val radioButton = sharedPreferences!!.getInt(REMINDERS_RADIO, 0)
            reminders_radio_group.check(radioButton)

            timeFrom = sharedPreferences!!.getString(FROM_TIME, "07:00")!!
            timeTo = sharedPreferences!!.getString(TO_TIME, "21:00")!!
            reminders_time_from_select.text = timeFrom
            reminders_time_to_select.text = timeTo
//            println("timeFrom: $timeFrom")

            val position = sharedPreferences!!.getInt(REMINDERS_SPINNER, 0)
            reminders_spinner.setSelection(position)
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
            createTimePicker(activity!!, FROM_TIME, reminders_time_from_select,
                reminders_time_from_select.text.toString())
        }

        reminders_time_to_select.setOnClickListener {
            createTimePicker(activity!!, TO_TIME, reminders_time_to_select,
                reminders_time_to_select.text.toString())
        }
    }

    private fun setRemindersOnOff() {
        when (reminders_on_off.isChecked) {
            true -> {
                reminders_when_container.visibility = View.VISIBLE
                reminders_time_container.visibility = View.VISIBLE
                reminders_how_often_container.visibility = View.VISIBLE
                setAlarm()
            }
            false -> {
                reminders_when_container.visibility = View.INVISIBLE
                reminders_time_container.visibility = View.INVISIBLE
                reminders_how_often_container.visibility = View.INVISIBLE
                cancelAlarm()
            }
        }
        sharedPreferences!!.edit().putBoolean(REMINDERS_ON_OFF, reminders_on_off.isChecked).apply()
    }

    private fun setAlarm() {

        val timeFrom = sharedPreferences!!.getString(FROM_TIME, "07:00")!!
        val parsedTime: LocalTime = LocalTime.parse(timeFrom)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, parsedTime.hour)
        calendar.set(Calendar.MINUTE, parsedTime.minute)

        val alarmIntent = Intent(activity!!.applicationContext, NotificationReceiver::class.java)
        val pendingAlarmIntent = PendingIntent
            .getBroadcast(activity!!.applicationContext, 1, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE)
                as AlarmManager

        var position = 0
        if (sharedPreferences != null) {
            position = sharedPreferences!!.getInt(REMINDERS_SPINNER, 0)
        }

        val interval = when (position) {
            0 -> { AlarmManager.INTERVAL_HALF_HOUR / 2 }
            1 -> { AlarmManager.INTERVAL_HALF_HOUR }
            2 -> { AlarmManager.INTERVAL_HOUR }
            3 -> { AlarmManager.INTERVAL_HOUR * 2 }
            4 -> { AlarmManager.INTERVAL_HOUR * 4 }
            else -> AlarmManager.INTERVAL_HALF_HOUR / 2
        }

        // Use RTC (Real Time Zone) for alarms that are dependent on current locale
        // The wake up version is useful if it has a limited window to perform operations
        // If you simply need a alarm to fire at a particular interval, use elapsed real time ERT,
        // in general that is the better choice.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, interval,
            pendingAlarmIntent)
        println("Alarm interval is $interval")

        Log.i("ALARM", "Alarm is turned on")
        println("Alarm is set at ${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)} " +
                "with interval $interval")
    }

    private fun cancelAlarm() {
        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE)
                as AlarmManager
        val alarmIntent = Intent(activity!!.applicationContext, NotificationReceiver::class.java)
        val pendingAlarmIntent = PendingIntent
            .getBroadcast(activity!!.applicationContext, 1, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingAlarmIntent)
        Log.i("ALARM", "Alarm is turned off")
    }

    private fun createTimePicker(activity: FragmentActivity, const: String, textView: TextView,
                                 time: String) {
        // Parse time String to LocalTime
        val parsedTime: LocalTime = LocalTime.parse(time)

        // Create time picker
        val timePicker = TimePickerDialog(activity,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                // Do something with the time chosen by the user
                val selectedTime: LocalTime = LocalTime.of(hourOfDay, minute)
                val formatted = timeFormat.format(selectedTime)
                sharedPreferences?.edit()?.putString(const, formatted)?.apply()
                textView.text = formatted
                setAlarm()

            }, parsedTime.hour, parsedTime.minute, true)

        timePicker.show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        sharedPreferences!!.edit().putInt(REMINDERS_SPINNER, position).apply()
        setAlarm()

//        when (position) {
//            0 -> {
//                // 15 min
//                println("Position is $position")
//            }
//            1 -> {
//                // 30 min
//                println("Position is $position")
//            }
//            2 -> {
//                // 1 uur
//                println("Position is $position")
//            }
//            3 -> {
//                // 2 uur
//                println("Position is $position")
//            }
//            4 -> {
//                // 4 uur
//                println("Position is $position")
//            }
//        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Another interface callback
    }
}
