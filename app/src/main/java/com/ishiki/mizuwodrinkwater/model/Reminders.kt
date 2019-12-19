package com.ishiki.mizuwodrinkwater.model

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.ishiki.mizuwodrinkwater.services.FROM_TIME
import com.ishiki.mizuwodrinkwater.services.NotificationReceiver
import com.ishiki.mizuwodrinkwater.services.REMINDERS_SPINNER
import com.ishiki.mizuwodrinkwater.services.TO_TIME
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.timerTask

object Reminders {

    fun setAlarm(sharedPreferences: SharedPreferences, context: Context) {

        val timeFrom = sharedPreferences.getString(FROM_TIME, "07:00")!!
        val parsedTime: LocalTime = LocalTime.parse(timeFrom)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, parsedTime.hour)
        calendar.set(Calendar.MINUTE, parsedTime.minute)

        val alarmIntent = Intent(context.applicationContext, NotificationReceiver::class.java)
        val pendingAlarmIntent = PendingIntent
            .getBroadcast(context.applicationContext, 1, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE)
                as AlarmManager

        val interval = when (sharedPreferences.getInt(REMINDERS_SPINNER, 0)) {
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
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, interval,
            pendingAlarmIntent)
        println("Alarm interval is $interval")

        Log.i("ALARM", "Alarm is turned on")
        println("Alarm is set at ${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)} " +
                "with interval $interval")
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE)
                as AlarmManager
        val alarmIntent = Intent(context.applicationContext, NotificationReceiver::class.java)
        val pendingAlarmIntent = PendingIntent
            .getBroadcast(context.applicationContext, 1, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingAlarmIntent)
        Log.i("ALARM", "Alarm is turned off")
    }

    fun createTimePicker(activity: FragmentActivity, sharedPreferences: SharedPreferences,
                         context: Context, const: String, textView: TextView,
                         time: String) {

        val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

        // Parse time String to LocalTime
        val parsedTime: LocalTime = LocalTime.parse(time)

        // Create time picker
        @Suppress("UNUSED_ANONYMOUS_PARAMETER") val timePicker = TimePickerDialog(activity,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                // Do something with the time chosen by the user
                val selectedTime: LocalTime = LocalTime.of(hourOfDay, minute)
                val formatted = timeFormat.format(selectedTime)
                sharedPreferences.edit()?.putString(const, formatted)?.apply()
                textView.text = formatted
                cancelAlarm(context)
                setAlarm(sharedPreferences, context.applicationContext)

            }, parsedTime.hour, parsedTime.minute, true)

        timePicker.show()
    }

    fun endTime(sharedPreferences: SharedPreferences, context: Context) {

        val timeTo = sharedPreferences.getString(TO_TIME, "22:00")!!
        println("Executed?")
        val parsedTime: LocalTime = LocalTime.parse(timeTo)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, parsedTime.hour)
        calendar.set(Calendar.MINUTE, parsedTime.minute)

        val date = calendar.time

        Timer().schedule(timerTask { cancelAlarm(context) }, date)
    }
}