package com.ishiki.mizuwodrinkwater.activities

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
//        outState?.run {
//            putInt(EXTRA_DAILY, dailyTotal)
//        }
//    }

    private lateinit var dbHandler: DrinksDatabaseHandler

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.today -> {
                replaceFragment(TodayFragment())
                glassesAddButton.show()

                glassesAddButton.setOnClickListener {
                    Toast.makeText(this, "Clicked TODAY on Bottom App Bar", Toast.LENGTH_SHORT).show()
                }

                println("Opened TODAY from itemId")
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                replaceFragment(HistoryFragment())
                glassesAddButton.hide()
                println("Opened HISTORY from itemId")
                return@OnNavigationItemSelectedListener true
            }
            R.id.goal -> {
                replaceFragment(GoalFragment())
                glassesAddButton.hide()
                println("Opened GOAL from itemId")
                return@OnNavigationItemSelectedListener true
            }
            R.id.glasses -> {
                replaceFragment(GlassesFragment())
                glassesAddButton.show()

                glassesAddButton.setOnClickListener {
//                    Toast.makeText(this, "Clicked GLASSES on Bottom App Bar", Toast.LENGTH_SHORT).show()

                    // Create a drink
                    val createDrink = Drinks()
                    createDrink.image = "water02"
                    createDrink.volume = 500
                    saveToDatabase(createDrink)

                }

                println("Opened GLASSES from itemId")
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

//    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (intent.extras != null) {
            val getIntent = intent.extras?.getInt("loadFragment")
            bottomNavigation.selectedItemId = getIntent!!
        } else {
            replaceFragment(TodayFragment())
        }

        dbHandler = DrinksDatabaseHandler(this)

    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun saveToDatabase(drinks: Drinks) {
        dbHandler.createDrink(drinks)
    }

//    fun addGlass(view: View) {
//        Toast.makeText(this, "Clicked FAB on Glasses Fragment", Toast.LENGTH_LONG).show()
//    }

}