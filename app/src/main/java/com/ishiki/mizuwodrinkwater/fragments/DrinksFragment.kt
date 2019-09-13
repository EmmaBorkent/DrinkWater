package com.ishiki.mizuwodrinkwater.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.DrinksRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import kotlinx.android.synthetic.main.fragment_drinks.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class DrinksFragment : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DrinksRecyclerAdapter
    private lateinit var dbHandler: DrinksDatabaseHandler
    private val date: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drinks, container, false)
    }

    @SuppressLint("NewApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showItems()
        checkTodayDate()

        drinks_arrow_left.setOnClickListener {
            drinks_arrow_right.visibility = View.VISIBLE

            date.add(Calendar.DAY_OF_YEAR, -1)
            val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
            drinks_text_day.text = showHumanDate

            checkYesterdayDate()
        }

        drinks_arrow_right.setOnClickListener {
            date.add(Calendar.DAY_OF_YEAR, 1)
            val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
            drinks_text_day.text = showHumanDate

            checkTodayDate()
            checkYesterdayDate()
        }
    }

    private fun showItems() {
        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)

        var databaseDrinks: ArrayList<Drinks> = ArrayList()
        databaseDrinks.reverse()
        val drinksList: ArrayList<Drinks> = ArrayList()

        layoutManager = LinearLayoutManager(context!!.applicationContext)
        drinks_recyclerview.layoutManager = layoutManager

        adapter = DrinksRecyclerAdapter(drinksList, context!!.applicationContext) { item,
                                                                                    position ->
            Log.d("adapter", "the item ${item.image} has position $position")
        }
        drinks_recyclerview.adapter = adapter

        databaseDrinks = dbHandler.readAllDrinks()

        for (i in databaseDrinks.iterator()) {
            Log.d("database", "${i.id} + ${i.image}")
            val drink = Drinks()
            drink.id = i.id
            drink.image = i.image
            drink.volume = i.volume
            drink.time = i.time
            drinksList.add(drink)
        }

        adapter.notifyDataSetChanged()
    }

    private fun checkTodayDate() {
        val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
        val currentDate = Calendar.getInstance()
        val humanDate = Drinks().showHumanDate(currentDate.timeInMillis)
        if (showHumanDate == humanDate) {
            drinks_arrow_right.visibility = View.INVISIBLE
            drinks_text_day.text = getString(R.string.drinks_today)
        }
    }

    private fun checkYesterdayDate() {
        val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_YEAR, -1)
        val humanDate = Drinks().showHumanDate(currentDate.timeInMillis)
        if (showHumanDate == humanDate) {
            drinks_text_day.text = getString(R.string.drinks_yesterday)
        }
    }

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
}
