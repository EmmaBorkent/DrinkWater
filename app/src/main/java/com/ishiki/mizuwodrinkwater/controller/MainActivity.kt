package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
//import android.widget.ArrayAdapter
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.TodayDrinksAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_AMOUNT
import com.ishiki.mizuwodrinkwater.services.DataService.drinksToday
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_DAILY
//import com.ishiki.mizuwodrinkwater.utilities.EXTRA_LIST
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var dailyTotal = 0
    var waterAmount = 250
//    private var drinksToday: MutableList<String> = mutableListOf()
    private lateinit var adapter: TodayDrinksAdapter

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.run {
            putInt(EXTRA_DAILY, dailyTotal)
//            putStringArray(EXTRA_LIST, drinksToday.toTypedArray())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.run {
            dailyTotal = getInt(EXTRA_DAILY)
            mainTextDailyTotal.text = dailyTotal.toString()

//            val list = getStringArray(EXTRA_LIST)
//            if (list != null) {
//                drinksToday = list.toMutableList()
//            }
        }

        dailyTotal = intent.getIntExtra(EXTRA_DAILY, dailyTotal)
        mainTextDailyTotal.text = dailyTotal.toString()

        waterAmount = intent.getIntExtra(EXTRA_AMOUNT, waterAmount)
        mainWaterAmount.text = getString(R.string.basic_amount, waterAmount)

        // Must be in onCreate
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, drinksToday)
//        drinksTodayList.adapter = adapter
        adapter = TodayDrinksAdapter(this, drinksToday)
        drinksTodayList.adapter = adapter

        if (mainWaterAmount.text == "500 ml") {
            mainDrinkImage.setBackgroundResource(R.drawable.water02)
        }
    }

    fun addWaterClick(@Suppress("UNUSED_PARAMETER") view: View) {
        mainTextDailyTotal.text = addWater().toString()
//        drinksToday.add(0,"$waterAmount ml")
        if (mainWaterAmount.text == "250 ml") {
            drinksToday.add(0, Drinks("glass", "water01", "250", "ml"))
        } else {
            drinksToday.add(0, Drinks("bottle", "water02", "500", "ml"))
        }

        adapter.notifyDataSetChanged()

        // Print to check
//        println(drinksToday.toString())
    }

    fun removeWaterClick(@Suppress("UNUSED_PARAMETER") view: View) {
        if (dailyTotal > 0 || drinksToday.isNotEmpty()) {
            mainTextDailyTotal.text = removeWater().toString()
            drinksToday.removeAt(0)
            adapter.notifyDataSetChanged()

            // Print to check
//            println(drinksToday.toString())
        }
    }

//    fun deleteDrinkFromList(view: View) {
//        mainTextDailyTotal.text = removeWater().toString()
//
//        val count = adapter.count
//        for (i in drinksToday) {
//            drinksToday.remove(adapter.getItem())
//        }
//        if (waterAmount == 250) {
//            drinksToday.remove(Drinks("glass", "water01", "250", "ml"))
//        } else {
//            drinksToday.remove(Drinks("bottle", "water02", "500", "ml"))
//        }
////        adapter.notifyDataSetChanged()
////        drinksTodayList.invalidateViews()
//    }

//    int count = adapter.getCount();
//    for (int i = 0; i < count; i++) {
//        adapter.remove(adapter.getItem(i));
//    }

    fun setGlassClick(@Suppress("UNUSED_PARAMETER") view: View) {
        val setGlassIntent = Intent(this, SetGlassActivity::class.java)
        setGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
        startActivity(setGlassIntent)
    }

    private fun addWater(): Int {
        dailyTotal += waterAmount
        return dailyTotal
    }

    private fun removeWater(): Int {
        dailyTotal -= waterAmount
        return dailyTotal
    }
}