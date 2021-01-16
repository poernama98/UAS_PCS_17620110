package com.rizkypurnama.finalproject.eventdetail

import com.rizkypurnama.finalproject.model.Teams

interface DetailEventView {
    fun showLoading()
    fun hideLoading()
    fun showTeamHome(data: List<Teams>)
    fun showTeamAway(data: List<Teams>)
}