package com.ishiki.mizuwodrinkwater.controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.TodayDrinksAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DataService.bottle
import com.ishiki.mizuwodrinkwater.services.DataService.drinksToday
import com.ishiki.mizuwodrinkwater.services.DataService.glass
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_CURRENT
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_DAILY
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_SET
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dailyTotal = 0
    private var currentGlass = glass
    private lateinit var adapter: TodayDrinksAdapter
    lateinit var context: Context

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.run {
            putInt(EXTRA_DAILY, dailyTotal)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.run {
            dailyTotal = getInt(EXTRA_DAILY)
            mainTextDailyTotal.text = dailyTotal.toString()
        }

        dailyTotal = intent.getIntExtra(EXTRA_DAILY, dailyTotal)
        mainTextDailyTotal.text = dailyTotal.toString()

        if (intent.getParcelableExtra<Drinks>(EXTRA_SET) != null) {
            currentGlass = intent.getParcelableExtra(EXTRA_SET)
        }
        mainWaterAmount.text = currentGlass.volume

        adapter = TodayDrinksAdapter(this, drinksToday)
        drinksTodayList.adapter = adapter

        if (mainWaterAmount.text == "500") {
            mainDrinkImage.setBackgroundResource(R.drawable.water02)
        }
    }

    fun addWaterClick(@Suppress("UNUSED_PARAMETER") view: View) {
        mainTextDailyTotal.text = addWater().toString()

        if (mainWaterAmount.text == glass.volume) {
            drinksToday.add(0, glass)
        } else {
            drinksToday.add(0, bottle)
        }
        adapter.notifyDataSetChanged()

        // Print to check
        println("Added ${currentGlass.volume} ${currentGlass.unit}. List now contains ${drinksToday.size} items.")
    }

    fun removeWaterClick(@Suppress("UNUSED_PARAMETER") view: View) {
        if (dailyTotal > 0 || drinksToday.isNotEmpty()) {
            val volume = drinksToday[0].volume
            dailyTotal -= volume.toInt()
            mainTextDailyTotal.text = dailyTotal.toString()
            drinksToday.removeAt(0)
            adapter.notifyDataSetChanged()
        }
        println("Removed last input. List now contains ${drinksToday.size} items.")
    }

    fun setGlassClick(@Suppress("UNUSED_PARAMETER") view: View) {
        val setGlassIntent = Intent(this, SetGlassActivity::class.java)
        setGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
        setGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
        startActivity(setGlassIntent)
    }

    private fun addWater(): Int {
        dailyTotal += currentGlass.volume.toInt()
        return dailyTotal
    }
}