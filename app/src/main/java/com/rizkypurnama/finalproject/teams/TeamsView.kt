package com.rizkypurnama.finalproject.teams

import com.rizkypurnama.finalproject.model.Teams

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Teams>)
}