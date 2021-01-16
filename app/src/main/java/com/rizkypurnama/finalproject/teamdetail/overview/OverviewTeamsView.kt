package com.rizkypurnama.finalproject.teamdetail.overview

import com.rizkypurnama.finalproject.model.Teams

interface OverviewTeamsView {
    fun showLoading()
    fun hideLoading()
    fun showOverview(data: List<Teams>)
}