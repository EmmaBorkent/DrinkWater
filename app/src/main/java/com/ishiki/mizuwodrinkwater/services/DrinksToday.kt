package com.ishiki.mizuwodrinkwater.services

import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinkTypes.currentGlass
import kotlinx.android.synthetic.main.activity_main.*

object DrinksToday {

    var goal = 2000
    var dailyTotal = 0
    var drinksTodayList: MutableList<Drinks> = mutableListOf()

    fun addDrink() {
        dailyTotal += currentGlass.volume.toInt()
        drinksTodayList.add(0, currentGlass)

        // Print to check
        println("dailyTotal = $dailyTotal")
    }

    fun removeDrink(position: Int) {
        drinksTodayList.removeAt(position)
    }
}