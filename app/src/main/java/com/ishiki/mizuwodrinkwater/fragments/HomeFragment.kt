package com.ishiki.mizuwodrinkwater.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.activities.MainActivity
import com.ishiki.mizuwodrinkwater.adapters.AddDrinkAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.model.Glasses
import com.ishiki.mizuwodrinkwater.services.DataSetChanged
import com.ishiki.mizuwodrinkwater.services.OnItemClickListenerAddDrinkAdapter
import com.ishiki.mizuwodrinkwater.services.OnItemClickListenerGlassesAdapter

class HomeFragment : Fragment(), OnItemClickListenerGlassesAdapter, DataSetChanged,
    OnItemClickListenerAddDrinkAdapter {
    override fun onItemClickedAddDrink(glass: Glasses, position: Int) {

    }

    override fun onDataSetChanged() {

    }

    override fun onItemClicked(glass: Glasses, position: Int, dataSetChanged: DataSetChanged) {
        Glasses.readGlass(position)
        Toast.makeText(context, "Clicked an item in add drink RecyclerView", Toast.LENGTH_LONG).show()
    }

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: AddDrinkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
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
//            TodayDrinksRecyclerAdapter.OnItemClickListenerGlassesAdapter {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        val builder = Dialog(context!!)
//        builder.setContentView(R.layout.popup_edit_glass)
//        builder.setContentView(R.layout.popup_add_drink)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        layoutManager = LinearLayoutManager(this.context)
//        popup_add_drink_recycler_view.layoutManager = layoutManager
//        adapter = AddDrinkAdapter(DrinkTypes.glasses, this.context!!)
//        popup_add_drink_recycler_view.adapter = adapter
    }

    fun createAddDrinkDialog() {

        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.popup_add_drink)
        val popupAddDrink = dialog.findViewById(R.id.popup_add_drink_recycler_view) as RecyclerView
        val popupEditGlassesButton = dialog.findViewById(R.id.popup_add_drink_edit_glasses_button) as ImageButton

        layoutManager = LinearLayoutManager(dialog.context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        popupAddDrink.layoutManager = layoutManager
        adapter = AddDrinkAdapter(Glasses.glassesList, context!!, this)
        popupAddDrink.adapter = adapter

        dialog.show()
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog.window?.attributes = lp

        popupEditGlassesButton.setOnClickListener {
            Toast.makeText(context, "Clicked Add on Home Fragment", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
            (activity as MainActivity).replaceFragment(GlassesFragment())
        }
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
