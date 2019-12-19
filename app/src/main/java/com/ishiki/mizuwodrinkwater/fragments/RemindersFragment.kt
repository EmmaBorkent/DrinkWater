package com.ishiki.mizuwodrinkwater.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Reminders.cancelAlarm
import com.ishiki.mizuwodrinkwater.model.Reminders.createTimePicker
import com.ishiki.mizuwodrinkwater.model.Reminders.endTime
import com.ishiki.mizuwodrinkwater.model.Reminders.setAlarm
import com.ishiki.mizuwodrinkwater.services.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_reminders.*

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class RemindersFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var sharedPreferences: SharedPreferences? = null
//    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

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
            createTimePicker(activity!!, sharedPreferences!!, activity!!,
                FROM_TIME, reminders_time_from_select, reminders_time_from_select.text.toString())
        }

        reminders_time_to_select.setOnClickListener {
            createTimePicker(activity!!, sharedPreferences!!, activity!!,
                TO_TIME, reminders_time_to_select, reminders_time_to_select.text.toString())
        }

        endTime(sharedPreferences!!, activity!!)
    }

    private fun setRemindersOnOff() {
        when (reminders_on_off.isChecked) {
            true -> {
                reminders_when_container.visibility = View.VISIBLE
                reminders_time_container.visibility = View.VISIBLE
                reminders_how_often_container.visibility = View.VISIBLE
                setAlarm(sharedPreferences!!, activity!!)
            }
            false -> {
                reminders_when_container.visibility = View.INVISIBLE
                reminders_time_container.visibility = View.INVISIBLE
                reminders_how_often_container.visibility = View.INVISIBLE
                cancelAlarm(activity!!)
            }
        }
        sharedPreferences!!.edit().putBoolean(REMINDERS_ON_OFF, reminders_on_off.isChecked).apply()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        sharedPreferences!!.edit().putInt(REMINDERS_SPINNER, position).apply()
        cancelAlarm(activity!!)
        setAlarm(sharedPreferences!!, activity!!)

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
