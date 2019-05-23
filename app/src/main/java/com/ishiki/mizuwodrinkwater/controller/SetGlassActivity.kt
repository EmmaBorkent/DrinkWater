package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.GlassesAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DataService.drinks
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_CURRENT
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_DAILY
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_SET
import kotlinx.android.synthetic.main.activity_set_glass.*

class SetGlassActivity : AppCompatActivity() {

    private var dailyTotal = 0
    private lateinit var currentGlass: Drinks
    private lateinit var adapter: GlassesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_glass)

        dailyTotal = intent.getIntExtra(EXTRA_DAILY, dailyTotal)
        currentGlass = intent.getParcelableExtra(EXTRA_CURRENT)

        adapter = GlassesAdapter(this, drinks) { drink ->
            // Here goes the code that you want to happen when you click on it
            println("Selected ${drink.image}")

            when (drink.image) {
                "water01" -> currentGlass = drinks[0]
                "water02" -> currentGlass = drinks[1]
                "water03" -> currentGlass = drinks[2]
                else -> Toast.makeText(this, "No Glass Was Selected", Toast.LENGTH_LONG).show()
            }

            val setGlassIntent = Intent(this, MainActivity::class.java)
            setGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
            setGlassIntent.putExtra(EXTRA_SET, currentGlass)
            startActivity(setGlassIntent)
        }

        var spanCount = 2
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3
        }

        val layoutManager = GridLayoutManager(this, spanCount)
        setGlassListView?.layoutManager = layoutManager
        setGlassListView?.adapter = adapter

        // To check if the intent works
        println("Intent in SetGlassActivity ${currentGlass.image}")
    }

    fun createCustomClass(@Suppress("UNUSED_PARAMETER") view: View) {
        val customGlassIntent = Intent(this, CustomGlass::class.java)
        customGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
        startActivity(customGlassIntent)
    }
}
