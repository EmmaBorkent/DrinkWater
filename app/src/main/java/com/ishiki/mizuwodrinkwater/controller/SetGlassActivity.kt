package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DataService.bottle
import com.ishiki.mizuwodrinkwater.services.DataService.glass
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_CURRENT
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_DAILY
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_SET

class SetGlassActivity : AppCompatActivity() {

    private var dailyTotal = 0
    private lateinit var currentGlass: Drinks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_glass)

        dailyTotal = intent.getIntExtra(EXTRA_DAILY, dailyTotal)
        currentGlass = intent.getParcelableExtra(EXTRA_CURRENT)

        // To check if the intent works
        println("Currently using a ${currentGlass.glass}")
    }

    fun setGlass250(@Suppress("UNUSED_PARAMETER") view: View) {
        currentGlass = glass

        // Print to check
        println("Changed to ${currentGlass.glass}")

        val glass250Intent = Intent(this, MainActivity::class.java)
        glass250Intent.putExtra(EXTRA_DAILY, dailyTotal)
        glass250Intent.putExtra(EXTRA_SET, currentGlass)
        startActivity(glass250Intent)
    }

    fun setGlass500(@Suppress("UNUSED_PARAMETER") view: View) {
        currentGlass = bottle

        // Print to check
        println("Changed to ${currentGlass.glass}")

        val glass500Intent = Intent(this, MainActivity::class.java)
        glass500Intent.putExtra(EXTRA_DAILY, dailyTotal)
        glass500Intent.putExtra(EXTRA_SET, currentGlass)
        startActivity(glass500Intent)
    }
}
