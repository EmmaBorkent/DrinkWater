package com.ishiki.mizuwodrinkwater.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.AddDrinkAdapter
import com.ishiki.mizuwodrinkwater.adapters.GlassesAdapter
import com.ishiki.mizuwodrinkwater.services.DataSetChanged
import com.ishiki.mizuwodrinkwater.services.DrinkTypes
import com.ishiki.mizuwodrinkwater.services.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_glasses.*
import kotlinx.android.synthetic.main.popup_add_drink.*

class GlassesFragment : Fragment(), OnItemClickListener, DataSetChanged {

    override fun onDataSetChanged() {
        dataSetChange()
    }

    override fun onItemClicked(view: View, position: Int) {
        (activity as MainActivity).editGlass()
    }

//    private lateinit var dbHandler: DrinksDatabaseHandler
//    private lateinit var glassesList: ArrayList<Drinks>
//    private lateinit var glassListItem: ArrayList<Drinks>

//    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: GlassesAdapter

    private lateinit var layoutManager2: LinearLayoutManager
    private lateinit var adapter2: AddDrinkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_glasses, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        layoutManager = GridLayoutManager(context!!.applicationContext, 2)
//        glasses_recycler_view.layoutManager = layoutManager
//        adapter = GlassesAdapter(DrinkTypes.glasses, view!!.rootView.context, this, this)
//        glasses_recycler_view.adapter = adapter

        layoutManager2 = LinearLayoutManager(context!!.applicationContext, LinearLayoutManager.HORIZONTAL,
            false)
        glasses_recycler_view.layoutManager = layoutManager2
        adapter2 = AddDrinkAdapter(DrinkTypes.glasses, view!!.rootView.context)
        glasses_recycler_view.adapter = adapter2


//        adapter.notifyDataSetChanged()

//        showItems()
    }

    fun dataSetChange() {
        adapter.notifyDataSetChanged()
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
//
//    setGlassButton.setOnClickListener {
//        // Print to check
//        println("Into Intent on SetGlassActivity is ${DrinkTypes.currentGlass.image}")
//        val customGlassIntent = Intent(context, CustomGlassActivity::class.java)
////        customGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
////        customGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
//        startActivity(customGlassIntent)
//    }

}
