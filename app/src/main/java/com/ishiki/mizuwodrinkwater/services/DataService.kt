package com.ishiki.mizuwodrinkwater.services

import com.ishiki.mizuwodrinkwater.model.Drinks

// Here we want all the arrays that hold data
object DataService {

    var drinksToday: MutableList<String> = mutableListOf()

    val glasses = listOf<Drinks>(
        Drinks("glass", "water01", "250", "ml"),
        Drinks("bottle", "water02", "500", "ml")
    )
}