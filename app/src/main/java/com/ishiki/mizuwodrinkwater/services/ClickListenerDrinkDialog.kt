package com.ishiki.mizuwodrinkwater.services

import com.ishiki.mizuwodrinkwater.model.Glasses

interface ClickListenerDrinkDialog {
    fun onItemClickedAddDrink(glass: Glasses, position: Int)
}