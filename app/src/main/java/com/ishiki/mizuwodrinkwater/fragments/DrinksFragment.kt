package com.ishiki.mizuwodrinkwater.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.DrinksRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import kotlinx.android.synthetic.main.fragment_drinks.*
import java.util.*
import kotlin.collections.ArrayList

class DrinksFragment : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DrinksRecyclerAdapter
    private lateinit var dbHandler: DrinksDatabaseHandler
    private val date: Calendar = Calendar.getInstance()
    private var minusOne = -1

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

        val showDate = Drinks().showHumanDate(date.timeInMillis)
        Log.d("time", "Today's date is $showDate")
        checkTodayDate()
        showItems()

        drinks_arrow_left.setOnClickListener {
            drinks_arrow_right.visibility = View.VISIBLE

            date.add(Calendar.DAY_OF_YEAR, -1)
            val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
            drinks_text_day.text = showHumanDate
            Log.d("time", "Left arrow date is $showHumanDate")

            minusOne -= 1
            showItems()

            checkYesterdayDate()
        }

        drinks_arrow_right.setOnClickListener {
            date.add(Calendar.DAY_OF_YEAR, 1)
            val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
            drinks_text_day.text = showHumanDate
            Log.d("time", "Right arrow date is $showHumanDate")

            minusOne += 1
            showItems()

            checkTodayDate()
            checkYesterdayDate()
        }
    }

    private fun showItems() {
        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)

        layoutManager = LinearLayoutManager(context!!.applicationContext)
        drinks_recyclerview.layoutManager = layoutManager

        val drinksList: ArrayList<Drinks> = ArrayList()
        adapter = DrinksRecyclerAdapter(drinksList, context!!.applicationContext) { item,
                                                                                    position ->
            Log.d("adapter", "the item ${item.image} has position $position")
        }
        drinks_recyclerview.adapter = adapter

        val databaseDrinks: ArrayList<Drinks>

        val showDate = Drinks().showHumanDate(date.timeInMillis)
        Log.d("time", "After showItems the date is $showDate")

        val to = Calendar.getInstance()
        to.add(Calendar.DAY_OF_YEAR, minusOne)

        databaseDrinks = dbHandler.findDay(date.timeInMillis, to.timeInMillis)

        for (i in databaseDrinks.iterator()) {
            Log.d("database", "${i.id} + ${i.image}")
            val drink = Drinks()
            drink.id = i.id
            drink.image = i.image
            drink.volume = i.volume
            drink.time = i.time
            drinksList.add(drink)
        }
        drinksList.reverse()

        adapter.notifyDataSetChanged()
    }

    // Cannot combine these functions because of limitations using .timeInMillis

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
