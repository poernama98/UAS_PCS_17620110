package com.rizkypurnama.finalproject.db

data class Favorite(val id_favorit: Long?, val id_event: String?, val league: String?, val homeScore: String?, val awayScore: String?, val homeTeam: String?,
                    val awayTeam: String?, val homeGoal: String?, val awayGoal: String?, val homeYellowCard: String?,
                    val awayYellowCard: String?,
                    val homeRedCard: String?, val awayRedCard: String?, val homeShots: String?, val awayShots: String?,
                    val homeGk: String?, val awayGk: String?, val homeDefense: String?, val awayDefense: String?,
                    val homeMidfield: String?, val awayMidfield: String?, val homeForward: String?, val awayForward: String?,
                    val homeSubtitutes: String?, val awaySubtitutes: String?, val IdHomeTeam: String?, val IdAwayTeam: String?,
                    val date: String?, val time: String?) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID_FAVORITE: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val LEAGUE: String = "LEAGUE"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_GOAL: String = "HOME_GOAL"
        const val AWAY_GOAL: String = "AWAY_GOAL"
        const val HOME_YELLOW_CARD: String = "HOME_YELLOW_CARD"
        const val AWAY_YELLOW_CARD: String = "AWAY_YELLOW_CARD"
        const val HOME_RED_CARD: String = "HOME_RED_CARD"
        const val AWAY_RED_CARD: String = "AWAY_RED_CARD"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val HOME_GK: String = "HOME_GK"
        const val AWAY_GK: String = "AWAY_GK"
        const val HOME_DEFENSE: String = "HOME_DEFENSE"
        const val AWAY_DEFENSE: String = "AWAY_DEFENSE"
        const val HOME_MIDFIELD: String = "HOME_MIDFIELD"
        const val AWAY_MIDFIELD: String = "AWAY_MIDFIELD"
        const val HOME_FORWARD: String = "HOME_FORWARD"
        const val AWAY_FORWARD: String = "AWAY_FORWARD"
        const val HOME_SUBTITUTES: String = "HOME_SUBTITUTES"
        const val AWAY_SUBTITUTES: String = "AWAY_SUBTITUTES"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        const val DATE: String = "DATE"
        const val TIME: String = "TIME"
    }
}