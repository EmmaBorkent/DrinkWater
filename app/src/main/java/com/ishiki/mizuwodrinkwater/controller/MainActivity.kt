package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.TodayDrinksAdapter
import com.ishiki.mizuwodrinkwater.services.DataService.bottle
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_AMOUNT
import com.ishiki.mizuwodrinkwater.services.DataService.drinksToday
import com.ishiki.mizuwodrinkwater.services.DataService.glass
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_DAILY
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dailyTotal = 0
    private var waterAmount = 250
    private lateinit var adapter: TodayDrinksAdapter

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

        waterAmount = intent.getIntExtra(EXTRA_AMOUNT, waterAmount)
        mainWaterAmount.text = getString(R.string.basic_amount, waterAmount)

        adapter = TodayDrinksAdapter(this, drinksToday)
        drinksTodayList.adapter = adapter

        if (mainWaterAmount.text == "500 ml") {
            mainDrinkImage.setBackgroundResource(R.drawable.water02)
        }
    }

    fun addWaterClick(@Suppress("UNUSED_PARAMETER") view: View) {
        mainTextDailyTotal.text = addWater().toString()
        if (mainWaterAmount.text == "250 ml") {
//            drinksToday.add(0, Drinks("glass", "water01", "250", "ml"))
            drinksToday.add(0, glass)
        } else {
//            drinksToday.add(0, Drinks("bottle", "water02", "500", "ml"))
            drinksToday.add(0, bottle)
        }
        adapter.notifyDataSetChanged()
    }

    fun removeWaterClick(@Suppress("UNUSED_PARAMETER") view: View) {
        if (dailyTotal > 0 || drinksToday.isNotEmpty()) {
//            mainTextDailyTotal.text = removeWater().toString()
            val volume = drinksToday[0].volume
            dailyTotal -= volume.toInt()
            mainTextDailyTotal.text = dailyTotal.toString()
            drinksToday.removeAt(0)
            adapter.notifyDataSetChanged()
        }
    }

    fun setGlassClick(@Suppress("UNUSED_PARAMETER") view: View) {
        val setGlassIntent = Intent(this, SetGlassActivity::class.java)
        setGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
        startActivity(setGlassIntent)
    }

    private fun addWater(): Int {
        dailyTotal += waterAmount
        return dailyTotal
    }
}