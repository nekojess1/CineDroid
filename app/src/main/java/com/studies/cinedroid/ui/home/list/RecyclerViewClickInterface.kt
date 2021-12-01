package com.studies.cinedroid.ui.home.list

import com.studies.cinedroid.domain.model.response.Movies

interface RecyclerViewClickInterface {
    fun onItemClick(data: Movies)
}