package com.rizkypurnama.finalproject.event.last

import com.rizkypurnama.finalproject.model.Event

interface LastEventView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}