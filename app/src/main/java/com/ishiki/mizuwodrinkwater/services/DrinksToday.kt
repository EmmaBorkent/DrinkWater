package com.ishiki.mizuwodrinkwater.services

import android.content.SharedPreferences
import com.ishiki.mizuwodrinkwater.model.Drinks
//import com.ishiki.mizuwodrinkwater.services.DrinkTypes.currentGlass

object DrinksToday {

    var sharedPreferences: SharedPreferences? = null
    var goal = 2000
    var dailyTotal = 0

    var history_drinks_list: MutableList<Drinks> = mutableListOf()

//    fun addDrink() {
//        dailyTotal += currentGlass.volume
//        sharedPreferences!!.edit().putInt("dailyTotal", dailyTotal).apply()
//        history_drinks_list.add(0, currentGlass)
//        serializeDrinksTodayList()
//    }

//    fun removeDrink(position: Int) {
//        history_drinks_list.removeAt(position)
//        serializeDrinksTodayList()
//    }

//    private fun serializeDrinksTodayList() {
//        val image: ArrayList<String> = ArrayList()
//        val volume: ArrayList<String> = ArrayList()
//        val unit: ArrayList<String> = ArrayList()
//
//        for (drinks: Drinks in history_drinks_list) {
//            image.add(drinks.image)
//            volume.add(drinks.volume.toString())
////            unit.add(drinks.unit)
//        }
//
//        sharedPreferences?.edit()?.putString("image", ObjectSerializer.serialize(image))?.apply()
//        sharedPreferences?.edit()?.putString("volume", ObjectSerializer.serialize(volume))?.apply()
//        sharedPreferences?.edit()?.putString("unit", ObjectSerializer.serialize(unit))?.apply()
//    }
}