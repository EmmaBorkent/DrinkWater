package com.ishiki.mizuwodrinkwater.model
//
//import android.app.TimePickerDialog
//import android.widget.TextView
//import androidx.fragment.app.FragmentActivity
//import com.ishiki.mizuwodrinkwater.fragments.RemindersFragment
//import java.text.SimpleDateFormat
//import java.time.LocalTime
//import java.time.format.DateTimeFormatter
//import java.time.format.FormatStyle
//import java.util.*
//
//object Reminders {
//
//    private var timeFrom = "7:00 AM"
//    private var timeTo   = "9:00 PM"
//
//    val timeFromParsed: LocalTime = LocalTime.parse(timeFrom,
//        DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
//    val timeToParsed: LocalTime = LocalTime.parse(timeTo,
//        DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
//
//    private val timeFormat = SimpleDateFormat("HH:mm", Locale.GERMAN)
//
//    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
//    fun createTimePicker(activity: FragmentActivity, const: String, textView: TextView, parsedTime: LocalTime) {
//        // Create time picker
//        val timePicker = TimePickerDialog(activity,
//            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
//                // If we change the time in time picker we'll get the callback in OnTimeSetListener
//                // Do something with the time chosen by the user
//                val selectedTime = Calendar.getInstance()
//                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
//                selectedTime.set(Calendar.MINUTE, minute)
//
//                val formatted = timeFormat.format(selectedTime.time)
//                RemindersFragment().saveToSharedPrefs(const, formatted)
//                textView.text = formatted
//
//            }, parsedTime.hour, parsedTime.minute, true)
//
//        timePicker.show()
//    }
//}