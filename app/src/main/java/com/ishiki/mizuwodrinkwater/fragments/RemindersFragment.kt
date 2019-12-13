package com.ishiki.mizuwodrinkwater.fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class RemindersFragment : Fragment() {

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
            println("timeFrom: $timeFrom")
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
                    TODO("Create Function")
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


//        var howOften: Int = reminders_input.text.toString().toInt()
//
//        reminders_how_often_text.addTextChangedListener(object: TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                howOften = reminders_input.text.toString().toInt()
//                println("How often = $howOften")
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        })
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
        // TESTING REPEATING LOCAL NOTIFICATIONS ------------------------------------------

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 12)
        calendar.set(Calendar.MINUTE, 50)

        val alarmIntent = Intent(activity!!.applicationContext, NotificationReceiver::class.java)
        val pendingAlarmIntent = PendingIntent
            .getBroadcast(activity!!.applicationContext, 1, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE)
                as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_HOUR, pendingAlarmIntent)

        println("Alarm is set at ${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}")

        // --------------------------------------------------------------------------------
    }

    private fun cancelAlarm() {
        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE)
                as AlarmManager
        val alarmIntent = Intent(activity!!.applicationContext, NotificationReceiver::class.java)
        val pendingAlarmIntent = PendingIntent
            .getBroadcast(activity!!.applicationContext, 1, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingAlarmIntent)
        println("Alarm is canceled")
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

            }, parsedTime.hour, parsedTime.minute, true)

        timePicker.show()
    }
}
