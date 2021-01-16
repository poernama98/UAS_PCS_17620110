package com.rizkypurnama.finalproject.model

import com.google.gson.annotations.SerializedName
import com.rizkypurnama.finalproject.model.Event

class ResponseEventSearch(
        @SerializedName("event")
        val event: List<Event>)