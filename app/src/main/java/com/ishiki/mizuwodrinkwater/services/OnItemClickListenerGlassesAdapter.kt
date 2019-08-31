package com.ishiki.mizuwodrinkwater.services

import com.ishiki.mizuwodrinkwater.model.Glasses

interface OnItemClickListenerGlassesAdapter {
    fun onItemClicked(glass: Glasses, position: Int, dataSetChanged: DataSetChanged)
}