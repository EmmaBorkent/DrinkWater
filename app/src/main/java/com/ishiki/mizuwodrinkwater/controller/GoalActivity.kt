package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.services.DrinksToday
import kotlinx.android.synthetic.main.activity_goal.*

class GoalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)
    }

    fun calculateGoal(@Suppress("UNUSED_PARAMETER") view: View) {
        val weight = goalWeightInput.text.toString()

        if (weight.isNotEmpty()) {
            val calculateGoal = weight.toInt()*32.56
            DrinksToday.goal = calculateGoal.toInt()
        } else {
            val toast = Toast.makeText(this, "Please enter your weight to calculate your daily goal",
                Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun setGoal(@Suppress("UNUSED_PARAMETER") view: View) {

        val goal = goalWeightInput.text.toString()

        if (goal.isNotEmpty()) {
            DrinksToday.goal = goal.toInt()

            val setGoalIntent = Intent(this, MainActivity::class.java)
            startActivity(setGoalIntent)
        } else {
            val toast = Toast.makeText(this, "Please enter a daily goal", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}
