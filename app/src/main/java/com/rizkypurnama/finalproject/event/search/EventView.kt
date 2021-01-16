package com.rizkypurnama.finalproject.event.search

import com.rizkypurnama.finalproject.model.Event

interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}