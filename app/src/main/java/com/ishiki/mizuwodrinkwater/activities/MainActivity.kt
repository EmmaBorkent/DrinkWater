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
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import com.ishiki.mizuwodrinkwater.services.EXTRA_CHECK
import com.ishiki.mizuwodrinkwater.services.EXTRA_GLASS
import com.ishiki.mizuwodrinkwater.services.EXTRA_VOLUME
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var dbHandler: DrinksDatabaseHandler

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

        dbHandler = DrinksDatabaseHandler(this)

        dbHandler.getCount()
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
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
            val drinksDialogIntent = Intent(applicationContext, AddDrinksDialogActivity::class.java)
            startActivity(drinksDialogIntent)
        }
    }

    private fun replaceGlassesFragment() {
        replaceFragment(GlassesFragment())
        glassesAddButton.show()
        glassesAddButton.setOnClickListener {
            val glassesDialogIntent = Intent(applicationContext, GlassesDialogActivity::class.java)
            glassesDialogIntent.putExtra(EXTRA_GLASS, "water01")
            glassesDialogIntent.putExtra(EXTRA_CHECK, "fab")
            glassesDialogIntent.putExtra(EXTRA_VOLUME, 250)
            startActivity(glassesDialogIntent)
        }
    }
}
