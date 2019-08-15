package com.ishiki.mizuwodrinkwater.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.DrinksRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import kotlinx.android.synthetic.main.fragment_today.*

class TodayFragment : Fragment() {

    private lateinit var dbHandler: DrinksDatabaseHandler
    private lateinit var glassesList: ArrayList<Drinks>
    private lateinit var glassListItem: ArrayList<Drinks>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: DrinksRecyclerAdapter

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
//        DrinksToday.drinksTodayList.clear()
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
//                    DrinksToday.drinksTodayList.add(Drinks(image[i], volume[i], unit[i]))
//                }
//            }
//        }

//        val resourceId = resources.getIdentifier(DrinkTypes.currentGlass.image, "drawable")
//        mainDrinkImage?.setBackgroundResource(resourceId)
//        mainWaterAmount?.text = DrinkTypes.currentGlass.volume
//        mainTextDailyTotal?.text = DrinksToday.dailyTotal.toString()
//        mainTextGoalNumber?.text = DrinksToday.goal.toString()

//        adapter = TodayDrinksRecyclerAdapter(MainActivity(), DrinksToday.drinksTodayList, object :
//            TodayDrinksRecyclerAdapter.OnItemClickListener {
//            override fun onItemClick(dailyTotal: Int) {
//                mainTextDailyTotal.text = dailyTotal.toString()
//            }
//        })
//        drinksTodayList?.adapter = adapter
//        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
//        drinksTodayList?.layoutManager = layoutManager
//        drinksTodayList?.setHasFixedSize(true)

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showItems()

    }

    private fun showItems() {

        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)

        glassesList = ArrayList()
        glassesList.reverse()
        glassListItem = ArrayList()

        layoutManager = LinearLayoutManager(context!!.applicationContext)
        drinksTodayList.layoutManager = layoutManager
//        adapter = DrinksRecyclerAdapter(glassListItem, context!!.applicationContext, object : DrinksRecyclerAdapter.OnItemClickListener {
//            override fun onItemClick(adapter: DrinksRecyclerAdapter) {
//                adapter.notifyDataSetChanged()
//            }
//        })

        adapter = DrinksRecyclerAdapter(glassListItem, context!!.applicationContext)
//        adapter = DrinksRecyclerAdapter(glassListItem, context!!.applicationContext, object : DrinksRecyclerAdapter.OnItemClickListener {
//            override fun onItemClick() {
//                mainTextDailyTotal.text = dailyTotal.toString()
//            }
//        })

        drinksTodayList.adapter = adapter

        glassesList = dbHandler.readAllDrinks()

        for (i in glassesList.iterator()) {
            val drink = Drinks(i.image, i.volume)
//            drink.image =
//            drink.volume =

            glassListItem.add(drink)
        }

        adapter.notifyDataSetChanged()

    }

    fun addDrink() {
        // Create a drink
        val createDrink = Drinks("water02", 500)
//        createDrink.image =
//        createDrink.volume =
        saveToDatabase(createDrink)
        adapter.notifyDataSetChanged()
    }

    private fun saveToDatabase(drinks: Drinks) {
        dbHandler.createDrink(drinks)
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
