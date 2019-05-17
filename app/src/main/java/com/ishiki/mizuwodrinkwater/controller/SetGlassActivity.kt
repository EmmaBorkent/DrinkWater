package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.GlassesAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DataService
import com.ishiki.mizuwodrinkwater.services.DataService.bottle
import com.ishiki.mizuwodrinkwater.services.DataService.glass
import com.ishiki.mizuwodrinkwater.services.DataService.juice
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_CURRENT
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_DAILY
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_SET
import kotlinx.android.synthetic.main.activity_set_glass.*

class SetGlassActivity : AppCompatActivity() {

    private var dailyTotal = 0
    private lateinit var currentGlass: Drinks
    lateinit var adapter: GlassesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_glass)

        dailyTotal = intent.getIntExtra(EXTRA_DAILY, dailyTotal)
        currentGlass = intent.getParcelableExtra(EXTRA_CURRENT)
        adapter = GlassesAdapter(this, DataService.drinks) { drink ->
            // Here goes the code that you want to happen when you click on it
            println(drink.glass)

            when (drink.glass) {
                "glass" -> currentGlass = glass
                "bottle" -> currentGlass = bottle
                "juice" -> currentGlass = juice
                else -> Toast.makeText(this, "No Glass Was Selected", Toast.LENGTH_LONG).show()
            }

            val setGlassIntent = Intent(this, MainActivity::class.java)
            setGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
            setGlassIntent.putExtra(EXTRA_SET, currentGlass)
            startActivity(setGlassIntent)
        }

//        adapter = GlassesAdapter(this, DataService.drinks) {
//
//            drink ->
//            println(drink.glass)
//
//            when (drink.glass) {
//                "glass" -> currentGlass = glass
//                "bottle" -> currentGlass = bottle
//                "juice" -> currentGlass = juice
//                else -> Toast.makeText(this, "No Glass Was Selected", Toast.LENGTH_LONG).show()
//            }
//
//            val setGlassIntent = Intent(this, MainActivity::class.java)
//            setGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
//            setGlassIntent.putExtra(EXTRA_SET, currentGlass)
//            startActivity(setGlassIntent)
//        }
        val layoutManager = GridLayoutManager(this, 2)
        setGlassListView?.layoutManager = layoutManager
        setGlassListView?.adapter = adapter

        // To check if the intent works
        println("Currently using a ${currentGlass.glass}")
    }

//    fun setGlass250(@Suppress("UNUSED_PARAMETER") view: View) {
//        currentGlass = glass
//
//        // Print to check
//        println("Changed to ${currentGlass.glass}")
//
//        val glass250Intent = Intent(this, MainActivity::class.java)
//        glass250Intent.putExtra(EXTRA_DAILY, dailyTotal)
//        glass250Intent.putExtra(EXTRA_SET, currentGlass)
//        startActivity(glass250Intent)
//    }
//
//    fun setGlass500(@Suppress("UNUSED_PARAMETER") view: View) {
//        currentGlass = bottle
//
//        // Print to check
//        println("Changed to ${currentGlass.glass}")
//
//        val glass500Intent = Intent(this, MainActivity::class.java)
//        glass500Intent.putExtra(EXTRA_DAILY, dailyTotal)
//        glass500Intent.putExtra(EXTRA_SET, currentGlass)
//        startActivity(glass500Intent)
//    }
//
//    fun setGlassJuice(@Suppress("UNUSED_PARAMETER") view: View) {
//        currentGlass = juice
//
//        // Print to check
//        println("Changed to ${currentGlass.glass}")
//
//        val glassJuiceIntent = Intent(this, MainActivity::class.java)
//        glassJuiceIntent.putExtra(EXTRA_DAILY, dailyTotal)
//        glassJuiceIntent.putExtra(EXTRA_SET, currentGlass)
//        startActivity(glassJuiceIntent)
//    }
}
