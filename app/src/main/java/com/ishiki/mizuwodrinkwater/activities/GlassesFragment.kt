package com.ishiki.mizuwodrinkwater.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.GlassesAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinkTypes
import com.ishiki.mizuwodrinkwater.services.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_glasses.*

class GlassesFragment : Fragment(), OnItemClickListener {

    override fun onItemClicked(view: View) {
        (activity as MainActivity).editGlass()
    }

    //    private lateinit var dbHandler: DrinksDatabaseHandler
//    private lateinit var glassesList: ArrayList<Drinks>
//    private lateinit var glassListItem: ArrayList<Drinks>
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: GlassesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_glasses, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        glassesAddButton.setOnClickListener {
//
//        }

//        setGlassButton.setOnClickListener {
//            // Print to check
//            println("Into Intent on SetGlassActivity is ${DrinkTypes.currentGlass.image}")
//            val customGlassIntent = Intent(context, CustomGlassActivity::class.java)
////        customGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
////        customGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
//            startActivity(customGlassIntent)
//        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManager = GridLayoutManager(context!!.applicationContext, 2)
        glasses_recycler_view.layoutManager = layoutManager
        adapter = GlassesAdapter(DrinkTypes.glasses, context!!.applicationContext, this)
        glasses_recycler_view.adapter = adapter

//        showItems()
    }


//    private fun showItems() {
//
//        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)
//
//        glassesList = ArrayList()
//        glassesList.reverse()
//        glassListItem = ArrayList()
//
//        layoutManager = GridLayoutManager(context!!.applicationContext, 2)
//        glasses_recycler_view.layoutManager = layoutManager
//        adapter = GlassesAdapter(glassListItem, context!!.applicationContext)
//        glasses_recycler_view.adapter = adapter
//
//        glassesList = dbHandler.readAllDrinks()
//
//        for (i in glassesList.iterator()) {
//            val drink = Drinks(i.image, i.volume)
////            drink.image =
////            drink.volume =
//
//            glassListItem.add(drink)
//        }
//
//        adapter.notifyDataSetChanged()
//
//    }



}
