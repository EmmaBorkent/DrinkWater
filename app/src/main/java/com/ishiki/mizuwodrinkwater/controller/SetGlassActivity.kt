package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_AMOUNT
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_DAILY
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_LIST

class SetGlassActivity : AppCompatActivity() {

    var dailyTotal = 0
    var waterAmount = 0
//    var drinksToday: Array<String>? = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_glass)

        dailyTotal = intent.getIntExtra(EXTRA_DAILY, 0)
//        drinksToday = intent.getStringArrayExtra(EXTRA_LIST)
        println("The total is $dailyTotal")
//        println(drinksToday.toString())

//        waterAmount = intent.getIntExtra(EXTRA_AMOUNT, 250)
//        println(waterAmount)
    }

    fun setGlass250(@Suppress("UNUSED_PARAMETER") view: View) {
        waterAmount = 250
        println("Set Glass to 250 ml")

        val glass250Intent = Intent(this, MainActivity::class.java)
        glass250Intent.putExtra(EXTRA_AMOUNT, waterAmount)
        glass250Intent.putExtra(EXTRA_DAILY, dailyTotal)
//        glass250Intent.putExtra(EXTRA_LIST, drinksToday)
        startActivity(glass250Intent)
    }

    fun setGlass500(@Suppress("UNUSED_PARAMETER") view: View) {
        waterAmount = 500
        println("Set Glass to 500 ml")

        val glass500Intent = Intent(this, MainActivity::class.java)
        glass500Intent.putExtra(EXTRA_AMOUNT, waterAmount)
        glass500Intent.putExtra(EXTRA_DAILY, dailyTotal)
//        glass500Intent.putExtra(EXTRA_LIST, drinksToday)
        startActivity(glass500Intent)
    }
}
