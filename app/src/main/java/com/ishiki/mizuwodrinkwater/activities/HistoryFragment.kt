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
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private lateinit var glassesList: ArrayList<Drinks>
    private lateinit var glassListItem: ArrayList<Drinks>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: DrinksRecyclerAdapter
    private lateinit var dbHandler: DrinksDatabaseHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
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
        history_drinks_list.layoutManager = layoutManager

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

        history_drinks_list.adapter = adapter

        glassesList = dbHandler.readAllDrinks()

        for (i in glassesList.iterator()) {
            val drink = Drinks(i.image, i.volume)
//            drink.image =
//            drink.volume =

            glassListItem.add(drink)
        }

        adapter.notifyDataSetChanged()

    }

    private fun saveToDatabase(drinks: Drinks) {
        dbHandler.createDrink(drinks)
    }
}
