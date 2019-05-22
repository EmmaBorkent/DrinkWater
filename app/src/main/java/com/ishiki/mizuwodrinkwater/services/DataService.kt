package com.ishiki.mizuwodrinkwater.services

import com.ishiki.mizuwodrinkwater.model.Drinks

// Here we want all the arrays that hold data
object DataService {

    // Put it back in ActivityMain, because when exiting the app, the list is preserved, but the dailyTotal is not.
    // I have to fix this, but for the time being it is best if the app losses all state when exiting.
    // When putting it back, remember to also put back the import.
    var drinksToday: MutableList<Drinks> = mutableListOf()

//    val drinks = listOf(
//        Drinks("water01", "250", "ml"),
//        Drinks("water02", "500", "ml"),
//        Drinks("water03", "150", "ml")
//    )

    val drinks = mutableListOf<Drinks>(
        Drinks("water01", "250", "ml"),
        Drinks("water02", "500", "ml"),
        Drinks("water03", "150", "ml")
    )
}