package com.rizkypurnama.finalproject.model

import com.google.gson.annotations.SerializedName
import com.rizkypurnama.finalproject.model.Event

data class ResponseEvent(
        @SerializedName("events")
        val events: List<Event>)