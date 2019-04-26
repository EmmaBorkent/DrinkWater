package com.ishiki.mizuwodrinkwater

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Adapter
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val drinksToday = mutableListOf<String>()
    lateinit var adapter: ArrayAdapter<String>

    private var dailyTotal = 0
    private val waterAmount = 250

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton.setOnClickListener {
            dailyTotalText.text = addWater().toString()
            drinksToday.add(0, "250 ml")
        }

        undoButton.setOnClickListener {
            dailyTotalText.text = removeWater().toString()
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, drinksToday)
        drinksTodayList.adapter = adapter
    }

    fun addWater(): Int {
        dailyTotal += waterAmount
        return dailyTotal
    }

    fun removeWater(): Int {
        dailyTotal -= waterAmount
        return dailyTotal
    }
}
