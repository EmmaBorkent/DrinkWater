package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.TodayDrinksRecyclerAdapter
import com.ishiki.mizuwodrinkwater.services.DrinkTypes.currentGlass
import com.ishiki.mizuwodrinkwater.services.DrinksToday
import com.ishiki.mizuwodrinkwater.services.DrinksToday.dailyTotal
import com.ishiki.mizuwodrinkwater.services.DrinksToday.goal
//import com.ishiki.mizuwodrinkwater.utilities.EXTRA_CURRENT
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_DAILY
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TodayDrinksRecyclerAdapter

//    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
//        outState?.run {
//            putInt(EXTRA_DAILY, dailyTotal)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        savedInstanceState?.run {
//            dailyTotal = getInt(EXTRA_DAILY)
//            mainTextDailyTotal.text = dailyTotal.toString()
//        }

//        dailyTotal = intent.getIntExtra(EXTRA_DAILY, dailyTotal)

//        if (intent.getParcelableExtra<Drinks>(EXTRA_SET) != null) {
//            currentGlass = intent.getParcelableExtra(EXTRA_SET)
//
//            // Print to check if intent works
//            println("Intent in MainActivity ${currentGlass.image}")
//        }

        val resourceId = resources.getIdentifier(currentGlass.image, "drawable", packageName)
        mainDrinkImage.setBackgroundResource(resourceId)
        mainWaterAmount.text = currentGlass.volume
        mainTextDailyTotal.text = dailyTotal.toString()
        mainTextGoalNumber.text = goal.toString()

        adapter = TodayDrinksRecyclerAdapter(this, DrinksToday.drinksTodayList, object : TodayDrinksRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(dailyTotal: Int) {
                mainTextDailyTotal.text = dailyTotal.toString()
            }
        })
        drinksTodayList.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        drinksTodayList?.layoutManager = layoutManager
        drinksTodayList?.setHasFixedSize(true)
    }

    fun addWaterClick(@Suppress("UNUSED_PARAMETER") view: View) {
        DrinksToday.addDrink()
        adapter.notifyDataSetChanged()
        mainTextDailyTotal.text = dailyTotal.toString()
        if (dailyTotal >= goal) {
            goalReached()
        }
    }

    fun setGlassClick(@Suppress("UNUSED_PARAMETER") view: View) {
        val setGlassIntent = Intent(this, SetGlassActivity::class.java)
        setGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
//        setGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
        startActivity(setGlassIntent)
    }

    fun setGoal(@Suppress("UNUSED_PARAMETER") view: View) {
        val setGoalIntent = Intent(this, GoalActivity::class.java)
        startActivity(setGoalIntent)
    }

    private fun goalReached() {
        val toast = Toast.makeText(this, "Congratulations! You reached your daily goal!", Toast.LENGTH_LONG)
        toast.show()
    }
}