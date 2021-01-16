package com.rizkypurnama.finalproject.teams.search

import com.rizkypurnama.finalproject.model.Teams

interface TeamsSearchView {
    fun showLoadingSearch()
    fun hideLoadingSearch()
    fun showEventListSearch(data: List<Teams>)
}