package com.rizkypurnama.finalproject.model

import com.google.gson.annotations.SerializedName

data class Event(
        @SerializedName("idEvent")
        val IdEvent: String? = null,

        @SerializedName("strEvent")
        val Event: String? = null,

        @SerializedName("strFilename")
        val FileName: String? = null,

        @SerializedName("strLeague")
        val League: String? = null,

        @SerializedName("strHomeTeam")
        val HomeTeam: String? = null,

        @SerializedName("strAwayTeam")
        val AwayTeam: String? = null,

        @SerializedName("intHomeScore")
        val HomeScore: String? = null,

        @SerializedName("intAwayScore")
        val AwayScore: String? = null,

        @SerializedName("strHomeGoalDetails")
        val HomeGoalDetails: String? = null,

        @SerializedName("strHomeRedCards")
        val HomeRedCards: String? = null,

        @SerializedName("strHomeYellowCards")
        val HomeYellowCards: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        val HomeLineupGoalkeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        val HomeLineupDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        val HomeLineupMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        val HomeLineupForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        val HomeLineupSubstitutes: String? = null,

        @SerializedName("strAwayGoalDetails")
        val AwayGoalDetails: String? = null,

        @SerializedName("strAwayRedCards")
        val AwayRedCards: String? = null,

        @SerializedName("strAwayYellowCards")
        val AwayYellowCards: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        val AwayLineupGoalkeeper: String? = null,

        @SerializedName("strAwayLineupDefense")
        val AwayLineupDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        val AwayLineupMidfield: String? = null,

        @SerializedName("strAwayLineupForward")
        val AwayLineupForward: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        val AwayLineupSubstitutes: String? = null,

        @SerializedName("intHomeShots")
        val HomeShots: String? = null,

        @SerializedName("intAwayShots")
        val AwayShots: String? = null,

        @SerializedName("idHomeTeam")
        val IdHomeTeam: String? = null,

        @SerializedName("idAwayTeam")
        val IdAwayTeam: String? = null,

        @SerializedName("dateEvent")
        val Date: String? = null,

        @SerializedName("strTime")
        val Time: String? = null)