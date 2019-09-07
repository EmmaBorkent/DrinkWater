package com.ishiki.mizuwodrinkwater.services

import com.ishiki.mizuwodrinkwater.model.Glasses
import com.ishiki.mizuwodrinkwater.unused.DataSetChanged

interface OnItemClickListenerGlassesAdapter {
    fun onItemClicked(glass: Glasses, position: Int, dataSetChanged: DataSetChanged)
}