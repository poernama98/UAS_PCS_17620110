package com.rizkypurnama.finalproject.event.next

import com.rizkypurnama.finalproject.model.Event

interface NextEventView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}