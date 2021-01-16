package com.rizkypurnama.finalproject.model

import com.google.gson.annotations.SerializedName

data class Teams(
        @SerializedName("idTeam")
        val teamId: String? = null,

        @SerializedName("strTeam")
        val teamName: String? = null,

        @SerializedName("strTeamBadge")
        val teamBadge: String? = null,

        @SerializedName("intFormedYear")
        val teamFormedYear: String? = null,

        @SerializedName("strStadium")
        val teamStadium: String? = null,

        @SerializedName("strDescriptionEN")
        val teamDescription: String? = null,

        @SerializedName("strStadiumThumb")
        val teamFenart1: String? = null,

        @SerializedName("strManager")
        val manager: String? = null,

        @SerializedName("strStadiumDescription")
        val stadiumDescription: String? = null,

        @SerializedName("strTeamJersey")
        val jersey: String? = null)