package com.ishiki.mizuwodrinkwater.services

import com.ishiki.mizuwodrinkwater.model.Glasses

interface OnItemClickListenerAddDrinkAdapter {
    fun onItemClickedAddDrink(glass: Glasses, position: Int)
}