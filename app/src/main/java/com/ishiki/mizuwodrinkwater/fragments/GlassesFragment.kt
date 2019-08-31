package com.ishiki.mizuwodrinkwater.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.activities.GlassesPopupActivity
import com.ishiki.mizuwodrinkwater.activities.MainActivity
import com.ishiki.mizuwodrinkwater.adapters.GlassesRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Glasses
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_GLASS
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_VOLUME
import kotlinx.android.synthetic.main.fragment_glasses.*

class GlassesFragment : Fragment() {

    private lateinit var layoutManager: GridLayoutManager
    lateinit var adapter: GlassesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_glasses, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManager = GridLayoutManager(context!!.applicationContext, 2)
        glasses_recycler_view.layoutManager = layoutManager
        adapter = GlassesRecyclerAdapter(Glasses.glassesList, context!!) { glass ->
//            Toast.makeText(context, "Clicked ${glass.image}", Toast.LENGTH_SHORT).show()
            val glassesPopupIntent = Intent(context, GlassesPopupActivity::class.java)
            glassesPopupIntent.putExtra(EXTRA_GLASS, glass.image)
            glassesPopupIntent.putExtra(EXTRA_VOLUME, glass.volume)
            startActivity(glassesPopupIntent)
        }
        glasses_recycler_view.adapter = adapter
    }
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
//        adapter = GlassesAdapterOLD2(glassListItem, context!!.applicationContext)
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
