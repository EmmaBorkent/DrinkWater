package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_AMOUNT
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_DAILY
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_LIST

class SetGlassActivity : AppCompatActivity() {

    var drinksToday: Array<String>? = arrayOf()
    var dailyTotal = 0
    var waterAmount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_glass)

        savedInstanceState?.run {
            dailyTotal = getInt(EXTRA_DAILY)
            drinksToday = getStringArray(EXTRA_LIST)
        }

        waterAmount = intent.getIntExtra(EXTRA_AMOUNT, 250)
        println(waterAmount)
    }

    fun setGlass250(@Suppress("UNUSED_PARAMETER") view: View) {
        waterAmount = 250
        println("Set Glass to 250 ml")

        val glass250Intent = Intent(this, MainActivity::class.java)
        glass250Intent.putExtra(EXTRA_AMOUNT, waterAmount)
        startActivity(glass250Intent)
    }

    fun setGlass500(@Suppress("UNUSED_PARAMETER") view: View) {
        waterAmount = 500
        println("Set Glass to 500 ml")

        val glass500Intent = Intent(this, MainActivity::class.java)
        glass500Intent.putExtra(EXTRA_AMOUNT, waterAmount)
        startActivity(glass500Intent)
    }
}
