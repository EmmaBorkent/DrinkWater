package com.ishiki.mizuwodrinkwater.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.activities.MainActivity
import com.ishiki.mizuwodrinkwater.model.Drinks.Companion.goal
import kotlinx.android.synthetic.main.fragment_goal.*

class GoalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        goalCalculate.setOnClickListener {
//            val weight = goalWeightInput.text.toString()
//
//            if (weight.isNotEmpty()) {
//                goal = weight.toInt() * 33
//                goalInput.setText(goal.toString())
//            } else {
//                val toast = Toast.makeText(MainActivity(), "Please enter your weight to calculate your daily goal",
//                    Toast.LENGTH_SHORT)
//                toast.show()
//            }
//        }

//        goalButtonSetGoal.setOnClickListener {
//            val setGoalIntent = Intent(MainActivity(), MainActivity::class.java)
//
//            if (goalInput.text.isNotEmpty()) {
//                goal = goalInput.text.toString().toInt()
////            DrinksToday.sharedPreferences!!.edit().putInt(DAILY_GOAL, goal).apply()
//                startActivity(setGoalIntent)
//            } else {
//                val toast = Toast.makeText(MainActivity(), "Please enter or calculate your daily goal", Toast.LENGTH_SHORT)
//                toast.show()
//            }
//        }
    }
}
