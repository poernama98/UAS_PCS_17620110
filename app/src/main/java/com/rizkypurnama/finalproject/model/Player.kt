package com.rizkypurnama.finalproject.model

import com.google.gson.annotations.SerializedName

data class Player (
        @SerializedName("strNationality")
        val Nationality: String? = null,

        @SerializedName("strPlayer")
        val Player: String? = null,

        @SerializedName("dateSigned")
        val DateSigned: String? = null,

        @SerializedName("strSigning")
        val Signing: String? = null,

        @SerializedName("strWage")
        val Wage: String? = null,

        @SerializedName("strDescriptionEN")
        val Description: String? = null,

        @SerializedName("strPosition")
        val Position: String? = null,

        @SerializedName("strHeight")
        val Height: String? = null,

        @SerializedName("strWeight")
        val Weight: String? = null,

        @SerializedName("strThumb")
        val Cutout: String? = null,

        @SerializedName("strFanart1")
        val Fanart2: String? = null)