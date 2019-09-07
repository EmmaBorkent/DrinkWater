package com.ishiki.mizuwodrinkwater.fragments

import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.fragment_drinks.*

class DrinksFragment : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: DrinksRecyclerAdapter
    private lateinit var dbHandler: DrinksDatabaseHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drinks, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showItems()
    }

    private fun showItems() {
        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)

        var databaseDrinks: ArrayList<Drinks> = ArrayList()
        databaseDrinks.reverse()
        val drinksList: ArrayList<Drinks> = ArrayList()

        layoutManager = LinearLayoutManager(context!!.applicationContext)
        drinks_recyclerview.layoutManager = layoutManager

//        adapter = DrinksRecyclerAdapter(drinksList, context!!.applicationContext, object : DrinksRecyclerAdapter.OnItemClickListenerGlassesAdapter {
//            override fun onItemClick(adapter: DrinksRecyclerAdapter) {
//                adapter.notifyDataSetChanged()
//            }
//        })

        adapter = DrinksRecyclerAdapter(drinksList, context!!.applicationContext) { item,
                                                                                    position ->
            Log.d("adapter", "the item ${item.image} has position $position")
        }
//        adapter = DrinksRecyclerAdapter(drinksList, context!!.applicationContext)
//        adapter = DrinksRecyclerAdapter(drinksList, context!!.applicationContext, object : DrinksRecyclerAdapter.OnItemClickListenerGlassesAdapter {
//            override fun onItemClick() {
//                mainTextDailyTotal.text = dailyTotal.toString()
//            }
//        })

        drinks_recyclerview.adapter = adapter

        databaseDrinks = dbHandler.readAllDrinks()

        for (i in databaseDrinks.iterator()) {
            Log.d("database", "${i.id} + ${i.image}")
            val drink = Drinks()
            drink.id = i.id
            drink.image = i.image
            drink.volume = i.volume
            drink.time = i.time
            drinksList.add(drink)
        }

        adapter.notifyDataSetChanged()
    }

    private fun saveToDatabase(drinks: Drinks) {
        dbHandler.createDrink(drinks)
    }
}
