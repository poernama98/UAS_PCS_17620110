package com.rizkypurnama.finalproject.model

import com.google.gson.annotations.SerializedName

class ResponseTeam(
        @SerializedName("teams")
        val teams: List<Teams>)