package com.ishiki.mizuwodrinkwater.Controller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.Utilities.EXTRA_DAILY
import com.ishiki.mizuwodrinkwater.Utilities.EXTRA_LIST
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var drinksToday: MutableList<String> = mutableListOf()
    private lateinit var adapter: ArrayAdapter<String>

    private var dailyTotal = 0
    private val waterAmount = 250

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {

            savedInstanceState.run {
                dailyTotal = getInt(EXTRA_DAILY)
                dailyTotalText.text = dailyTotal.toString()

                val list = getStringArray(EXTRA_LIST)
                if (list != null) {
                    drinksToday = list.toMutableList<String>()
                }
            }
        }

        // Moet in onCreate
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, drinksToday)
        drinksTodayList.adapter = adapter

    }

    fun addWaterClick(view: View) {
        dailyTotalText.text = addWater().toString()
        drinksToday.add(0, "250 ml")
        adapter.notifyDataSetChanged()

        // Print to check
        println(drinksToday.toString())
    }

    fun removeWaterClick(view: View) {
        if (dailyTotal > 0 || drinksToday.isEmpty() == false) {
            dailyTotalText.text = removeWater().toString()
            drinksToday.removeAt(0)
            adapter.notifyDataSetChanged()
        }
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