package com.rizkypurnama.finalproject.teamdetail.player

import com.rizkypurnama.finalproject.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Player>)
}