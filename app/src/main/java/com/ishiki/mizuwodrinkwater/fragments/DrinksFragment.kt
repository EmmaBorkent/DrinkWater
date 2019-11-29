package com.ishiki.mizuwodrinkwater.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import kotlinx.android.synthetic.main.fragment_drinks.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.DAY_OF_WEEK


class DrinksFragment : Fragment() {

    private lateinit var dbHandler: DrinksDatabaseHandler
    private val date: Calendar = Calendar.getInstance()
    @SuppressLint("SimpleDateFormat")
    private val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drinks, container, false)
    }

    @SuppressLint("NewApi", "SimpleDateFormat")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_drinks_notifications.setOnClickListener {
            Toast.makeText(context,"Notificaties", Toast.LENGTH_SHORT).show()
        }

        // Set first day of week to monday
        date[DAY_OF_WEEK] = Calendar.MONDAY

        // Print only to check date
        val df: DateFormat = SimpleDateFormat("EEE dd/MM/yyyy")


        val volumes = arrayListOf<Int>()
        for (i in 0..6) {

            val year = date.get(Calendar.YEAR)
            val month = date.get(Calendar.MONTH)+1
            val day = date.get(Calendar.DATE)

            volumes.add(calculateVolume(year, month, day))

            // Print only to check date
            println(df.format(date.time))
            date.add(Calendar.DATE, 1)
        }

        fragment_drinks_monday_volume.text = volumes[0].toString()
        fragment_drinks_tuesday_volume.text = volumes[1].toString()
        fragment_drinks_wednesday_volume.text = volumes[2].toString()
        fragment_drinks_thursday_volume.text = volumes[3].toString()
        fragment_drinks_friday_volume.text = volumes[4].toString()
        fragment_drinks_saturday_volume.text = volumes[5].toString()
        fragment_drinks_sunday_volume.text = volumes[6].toString()

    }

    private fun calculateVolume(year: Int, month: Int, day: Int): Int {
        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)

        val parseFrom = format.parse("$year-$month-$day 00:00:00")
        val parseTo = format.parse("$year-$month-$day 23:59:00")

        val databaseDrinks: ArrayList<Drinks>
        databaseDrinks = dbHandler.findDay(parseTo!!.time, parseFrom!!.time)

        var totalVolume = 0
        for (i in databaseDrinks.iterator()) {
            totalVolume += i.volume
        }
        return totalVolume
    }

}

//checkTodayDate()
//
//drinks_arrow_left.setOnClickListener {
//    drinks_arrow_right.visibility = View.VISIBLE
//    date.add(Calendar.DAY_OF_YEAR, -1)
//    val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
//    drinks_text_day.text = showHumanDate
//    minusOne -= 1
//    showItems()
//    checkYesterdayDate()
//}
//
//drinks_arrow_right.setOnClickListener {
//    date.add(Calendar.DAY_OF_YEAR, 1)
//    val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
//    drinks_text_day.text = showHumanDate
//    minusOne += 1
//    showItems()
//    checkTodayDate()
//    checkYesterdayDate()
//}

// Cannot combine these functions because of limitations using .timeInMillis
// Maybe I can, because now I can parse
//    private fun checkTodayDate() {
//        val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
//        val currentDate = Calendar.getInstance()
//        val humanDate = Drinks().showHumanDate(currentDate.timeInMillis)
//        if (showHumanDate == humanDate) {
//            drinks_arrow_right.visibility = View.INVISIBLE
//            drinks_text_day.text = getString(R.string.drinks_today)
//        }
//    }
//
//    private fun checkYesterdayDate() {
//        val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
//        val currentDate = Calendar.getInstance()
//        currentDate.add(Calendar.DAY_OF_YEAR, -1)
//        val humanDate = Drinks().showHumanDate(currentDate.timeInMillis)
//        if (showHumanDate == humanDate) {
//            drinks_text_day.text = getString(R.string.drinks_yesterday)
//        }
//    }

//    private fun saveToDatabase(drinks: Drinks) {
//        dbHandler.createDrink(drinks)
//    }

//    private fun date() {
//
//        val today = System.currentTimeMillis()
//        val showHumanDateToday = Drinks().showHumanDate(today)
//        Log.d("DrinksFragment", "Current time $showHumanDateToday")
//
//        val twoDaysAgo = Calendar.getInstance()
//        twoDaysAgo.add(Calendar.DAY_OF_YEAR, -2)
//        val showHumanDateTwoDaysAgo = Drinks().showHumanDate(twoDaysAgo.timeInMillis)
//        Log.d("DrinksFragment", "Two days ago $showHumanDateTwoDaysAgo")
//
//    }