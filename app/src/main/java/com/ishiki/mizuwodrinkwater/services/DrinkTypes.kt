package com.ishiki.mizuwodrinkwater.services

import com.ishiki.mizuwodrinkwater.model.Drinks

object DrinkTypes {

    val drinks = mutableListOf<Drinks>(
        Drinks("water01", "250", "ml"),
        Drinks("water02", "500", "ml"),
        Drinks("water03", "150", "ml")
    )

    var currentGlass = drinks[0]
}