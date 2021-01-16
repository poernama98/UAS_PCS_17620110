package com.rizkypurnama.finalproject.db

data class TeamFavorite(val idTeamFavorit: Long?, val idTeam: String?, val teamBadge: String?,
                        val teamFenart: String?, val teamName: String?) {
    companion object {
        const val TABLE_NAME: String = "TABLE_TEAM_FAVORITE"
        const val ID_TEAM_FAVORITE: String = "ID_TEAM_FAVORITE"
        const val ID_TEAM: String = "ID_TEAM"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_FENART: String = "TEAM_FENART"
        const val TEAM_NAME: String = "TEAM_NAME"
    }
}