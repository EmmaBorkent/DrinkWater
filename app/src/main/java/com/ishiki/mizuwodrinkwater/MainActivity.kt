package com.ishiki.mizuwodrinkwater

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dailyTotal = 0
    private val waterAmount = 250

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton.setOnClickListener {
            dailyTotalText.text = addWater().toString()
        }
    }

    fun addWater(): Int {
        dailyTotal += waterAmount
        return dailyTotal
    }

    fun addWaterToList() {
        
    }
}
