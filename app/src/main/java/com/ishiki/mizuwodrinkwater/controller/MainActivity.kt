package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.services.DataService
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_AMOUNT
//import com.ishiki.mizuwodrinkwater.services.DataService.drinksToday
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_DAILY
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_LIST
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var drinksToday: MutableList<String> = mutableListOf()
    private lateinit var adapter: ArrayAdapter<String>

    private var dailyTotal = 0
    var waterAmount = 250

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.run {
            dailyTotal = getInt(EXTRA_DAILY)
            mainTextDailyTotal.text = dailyTotal.toString()

            val list = getStringArray(EXTRA_LIST)
            if (list != null) {
                drinksToday = list.toMutableList()
            }
        }

        waterAmount = intent.getIntExtra(EXTRA_AMOUNT, 250)

        mainWaterAmount.text = waterAmount.toString()

        // Must be in onCreate
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, drinksToday)
        drinksTodayList.adapter = adapter
    }

    fun addWaterClick(@Suppress("UNUSED_PARAMETER") view: View) {
        mainTextDailyTotal.text = addWater().toString()
        drinksToday.add(0, "250 ml")
        adapter.notifyDataSetChanged()

        // Print to check
        println(drinksToday.toString())
    }

    fun removeWaterClick(@Suppress("UNUSED_PARAMETER") view: View) {
        if (dailyTotal > 0 || drinksToday.isNotEmpty()) {
            mainTextDailyTotal.text = removeWater().toString()
            drinksToday.removeAt(0)
            adapter.notifyDataSetChanged()

            // Print to check
            println(drinksToday.toString())
        }
    }

    fun setGlassClick(@Suppress("UNUSED_PARAMETER") view: View) {
        val setGlassIntent = Intent(this, SetGlassActivity::class.java)
        setGlassIntent.putExtra(EXTRA_AMOUNT, waterAmount)
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

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.run {
            putInt(EXTRA_DAILY, dailyTotal)
            putStringArray(EXTRA_LIST, drinksToday.toTypedArray())
        }
    }
}