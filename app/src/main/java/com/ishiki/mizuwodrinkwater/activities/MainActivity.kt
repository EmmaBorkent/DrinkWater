package com.ishiki.mizuwodrinkwater.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.fragments.DrinksFragment
import com.ishiki.mizuwodrinkwater.fragments.GlassesFragment
import com.ishiki.mizuwodrinkwater.fragments.GoalFragment
import com.ishiki.mizuwodrinkwater.fragments.HomeFragment
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_CHECK
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_GLASS
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_VOLUME
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private lateinit var dbHandler: DrinksDatabaseHandler

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.today -> {
                replaceHomeFragment()
                println("Opened TODAY from itemId")
                return@OnNavigationItemSelectedListener true
            }
            R.id.drinks -> {
                replaceFragment(DrinksFragment())
                glassesAddButton.hide()
                println("Opened HISTORY from itemId")
                return@OnNavigationItemSelectedListener true
            }
            R.id.glasses -> {
                replaceGlassesFragment()
                println("Opened GLASSES from itemId")
                return@OnNavigationItemSelectedListener true
            }
            R.id.goal -> {
                replaceFragment(GoalFragment())
                glassesAddButton.hide()
                println("Opened GOAL from itemId")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (intent.extras != null) {
            val getIntent = intent.extras?.getInt("loadFragment")
            bottomNavigation.selectedItemId = getIntent!!
        } else {
            replaceHomeFragment()
        }
//        dbHandler = DrinksDatabaseHandler(this)
    }

    fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun replaceHomeFragment() {
        replaceFragment(HomeFragment())
        glassesAddButton.show()
        glassesAddButton.setOnClickListener {
            Toast.makeText(this, "Clicked FAB on Home Fragment",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceGlassesFragment() {
        replaceFragment(GlassesFragment())
        glassesAddButton.show()
        glassesAddButton.setOnClickListener {
//            Toast.makeText(this, "Clicked FAB on Glasses Fragment",
//                Toast.LENGTH_SHORT).show()
            val glassesPopupIntent = Intent(applicationContext, GlassesPopupActivity::class.java)
            glassesPopupIntent.putExtra(EXTRA_GLASS, "water01")
            glassesPopupIntent.putExtra(EXTRA_CHECK, "fab")
            glassesPopupIntent.putExtra(EXTRA_VOLUME, 250)
            startActivity(glassesPopupIntent)
        }
    }
}


//    private fun saveToDatabase(drinks: Drinks) {
//        dbHandler.createDrink(drinks)
//    }

//    fun editGlass(glass: Glasses, position: Int, dataSetChanged: DataSetChanged) {
//        val popupFragment = GlassesDialog(glass, position, dataSetChanged)
//        popupFragment.show(supportFragmentManager, "edit glass")
//    }

//private fun homeFragment() {
//    replaceFragment(HomeFragment())
//    glassesAddButton.show()
//
//    glassesAddButton.setOnClickListener {
//
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.popup_add_drink)
//        val popupAddDrink = dialog.findViewById(R.id.popup_add_drink_recycler_view) as RecyclerView
//        val popupEditGlassesButton = dialog.findViewById(R.id.popup_add_drink_edit_glasses_button) as ImageButton
//
//        layoutManager = LinearLayoutManager(dialog.context)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        popupAddDrink.layoutManager = layoutManager
//        adapter = AddDrinkAdapter(DrinkTypes.glasses, this, this)
//        popupAddDrink.adapter = adapter
//
//        dialog.show()
//        val lp = WindowManager.LayoutParams()
//        lp.copyFrom(dialog.window?.attributes)
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT
//        dialog.window?.attributes = lp
//
//        popupEditGlassesButton.setOnClickListener {
//            Toast.makeText(this, "Clicked Add on Home Fragment", Toast.LENGTH_SHORT).show()
//
//            dialog.dismiss()
//            replaceFragment(GlassesFragment())
//        }
//
//        Create a drink
//        val createDrink = Drinks()
//        createDrink.image = "water02"
//        createDrink.volume = 500
//        saveToDatabase(createDrink)
//
//    }
//}