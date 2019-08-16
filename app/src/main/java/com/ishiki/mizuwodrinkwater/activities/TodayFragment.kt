package com.ishiki.mizuwodrinkwater.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks

class TodayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        DrinksToday.sharedPreferences = this.getSharedPreferences("com.ishiki.mizuwodrinkwater", 0)
//        DrinksToday.goal = DrinksToday.sharedPreferences!!.getInt(DAILY_GOAL, DrinksToday.goal)
//        DrinksToday.dailyTotal = DrinksToday.sharedPreferences!!.getInt("dailyTotal", DrinksToday.dailyTotal)
//        DrinkTypes.currentGlass = Drinks(
//            DrinksToday.sharedPreferences?.getString("currentGlassImage", DrinkTypes.currentGlass.image).toString(),
//            DrinksToday.sharedPreferences?.getString("currentGlassVolume", DrinkTypes.currentGlass.volume).toString(),
//            DrinksToday.sharedPreferences?.getString("currentGlassUnit", DrinkTypes.currentGlass.unit).toString()
//        )
//
//        DrinksToday.history_drinks_list.clear()
//
//        val image = ObjectSerializer.deserialize(
//            DrinksToday.sharedPreferences?.getString("image",
//                ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
//        val volume = ObjectSerializer.deserialize(
//            DrinksToday.sharedPreferences?.getString("volume",
//                ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
//        val unit = ObjectSerializer.deserialize(
//            DrinksToday.sharedPreferences?.getString("unit",
//                ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
//
//        if (image.size > 0 && volume.size > 0 && unit.size > 0) {
//            if (image.size == volume.size && image.size == unit.size) {
//
//                for ((i) in image.withIndex()) {
//                    DrinksToday.history_drinks_list.add(Drinks(image[i], volume[i], unit[i]))
//                }
//            }
//        }

//        val resourceId = resources.getIdentifier(DrinkTypes.currentGlass.image, "drawable")
//        mainDrinkImage?.setBackgroundResource(resourceId)
//        mainWaterAmount?.text = DrinkTypes.currentGlass.volume
//        mainTextDailyTotal?.text = DrinksToday.dailyTotal.toString()
//        mainTextGoalNumber?.text = DrinksToday.goal.toString()

//        adapter = TodayDrinksRecyclerAdapter(MainActivity(), DrinksToday.history_drinks_list, object :
//            TodayDrinksRecyclerAdapter.OnItemClickListener {
//            override fun onItemClick(dailyTotal: Int) {
//                mainTextDailyTotal.text = dailyTotal.toString()
//            }
//        })
//        history_drinks_list?.adapter = adapter
//        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
//        history_drinks_list?.layoutManager = layoutManager
//        history_drinks_list?.setHasFixedSize(true)

//        mainDrinkImage.setOnClickListener {
//            val setGlassIntent = Intent(context, SetGlassActivity::class.java)
//            setGlassIntent.putExtra(EXTRA_DAILY, DrinksToday.dailyTotal)
////          setGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
//            startActivity(setGlassIntent)
//        }

//        todayAddButton.setOnClickListener {
//            Toast.makeText(context?.applicationContext, "Clicked FAB on Today Fragment", Toast.LENGTH_LONG).show()
//        }

//        mainButtonAdd?.setOnClickListener {
//            DrinksToday.addDrink()
//            adapter.notifyDataSetChanged()
//            mainTextDailyTotal.text = DrinksToday.dailyTotal.toString()
//            if (DrinksToday.dailyTotal >= DrinksToday.goal) {
//                goalReached()
//            }
//        }

//        mainButtonSetGoal.setOnClickListener {
//            val setGoalIntent = Intent(context, GoalActivity::class.java)
//            startActivity(setGoalIntent)
//        }
    }

    fun addDrink() {
        // Create a drink
        val createDrink = Drinks("water02", 500)
//        createDrink.image =
//        createDrink.volume =
//        saveToDatabase(createDrink)
//        adapter.notifyDataSetChanged()
    }

//    private fun createPopup() {
//        val view = layoutInflater.inflate(R.layout.popup_edit_glass, null)
////        val drinkImage = view.popupGlassImage
////        val drinkVolume = view.popupVolume
//
//        dialogBuilder = AlertDialog.Builder(activity!!.applicationContext).setView(view)
//        dialog = dialogBuilder.create()
//        dialog.show()
//
//        popupSelectButton.setOnClickListener {
//
//        }
//    }

//    private fun goalReached() {
//        val toast = Toast.makeText(context, "Congratulations! You reached your daily goal!",
//            Toast.LENGTH_LONG)
//        toast.show()
//    }

}
