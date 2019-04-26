package com.ishiki.mizuwodrinkwater

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val drinksToday = mutableListOf<String>()

    private var dailyTotal = 0
    private val waterAmount = 250

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton.setOnClickListener {
            dailyTotalText.text = addWater().toString()
            drinksToday.add(0, "250 ml")
            println(drinksToday)
        }
    }

    fun addWater(): Int {
        dailyTotal += waterAmount
        return dailyTotal
    }
}
