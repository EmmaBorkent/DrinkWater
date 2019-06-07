package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.TodayDrinksRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinkTypes.currentGlass
import com.ishiki.mizuwodrinkwater.services.DrinksToday
import com.ishiki.mizuwodrinkwater.services.DrinksToday.dailyTotal
import com.ishiki.mizuwodrinkwater.services.DrinksToday.goal
import com.ishiki.mizuwodrinkwater.services.DrinksToday.sharedPreferences
import com.ishiki.mizuwodrinkwater.services.ObjectSerializer
import com.ishiki.mizuwodrinkwater.utilities.DAILY_GOAL
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

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences("com.ishiki.mizuwodrinkwater", 0)
        goal = sharedPreferences!!.getInt(DAILY_GOAL, goal)
        dailyTotal = sharedPreferences!!.getInt("dailyTotal", dailyTotal)

        DrinksToday.drinksTodayList.clear()

        val image = ObjectSerializer.deserialize(sharedPreferences?.getString("image",
            ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
        val volume = ObjectSerializer.deserialize(sharedPreferences?.getString("volume",
            ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
        val unit = ObjectSerializer.deserialize(sharedPreferences?.getString("unit",
            ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>

        if (image.size > 0 && volume.size > 0 && unit.size > 0) {
            if (image.size == volume.size && image.size == unit.size) {

                for ((i) in image.withIndex()) {
                    DrinksToday.drinksTodayList.add(Drinks(image[i], volume[i], unit[i]))
                }
            }
        }

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