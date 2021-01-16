package com.rizkypurnama.finalproject.model

import com.google.gson.annotations.SerializedName
import com.rizkypurnama.finalproject.model.Player

class ResponsePlayer(
        @SerializedName("player")
        val player: List<Player>)