package com.rizkypurnama.finalproject.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context, "finalproject.db", null, 1) {
    companion object {
        private var instance: MyDatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabaseHelper {
            if (instance == null) {
                instance = MyDatabaseHelper(context)
            }
            return instance as MyDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            TeamFavorite.TABLE_NAME, true,
                TeamFavorite.ID_TEAM_FAVORITE to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamFavorite.ID_TEAM to TEXT + UNIQUE,
                TeamFavorite.TEAM_BADGE to TEXT,
                TeamFavorite.TEAM_FENART to TEXT,
                TeamFavorite.TEAM_NAME to TEXT)

        db?.createTable(
            Favorite.TABLE_FAVORITE, true,
                Favorite.ID_FAVORITE to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.ID_EVENT to TEXT + UNIQUE,
                Favorite.LEAGUE to TEXT,
                Favorite.HOME_SCORE to TEXT,
                Favorite.AWAY_SCORE to TEXT,
                Favorite.HOME_TEAM to TEXT,
                Favorite.AWAY_TEAM to TEXT,
                Favorite.HOME_GOAL to TEXT,
                Favorite.AWAY_GOAL to TEXT,
                Favorite.HOME_YELLOW_CARD to TEXT,
                Favorite.AWAY_YELLOW_CARD to TEXT,
                Favorite.HOME_RED_CARD to TEXT,
                Favorite.AWAY_RED_CARD to TEXT,
                Favorite.HOME_SHOTS to TEXT,
                Favorite.AWAY_SHOTS to TEXT,
                Favorite.HOME_GK to TEXT,
                Favorite.AWAY_GK to TEXT,
                Favorite.HOME_DEFENSE to TEXT,
                Favorite.AWAY_DEFENSE to TEXT,
                Favorite.HOME_MIDFIELD to TEXT,
                Favorite.AWAY_MIDFIELD to TEXT,
                Favorite.HOME_FORWARD to TEXT,
                Favorite.AWAY_FORWARD to TEXT,
                Favorite.HOME_SUBTITUTES to TEXT,
                Favorite.AWAY_SUBTITUTES to TEXT,
                Favorite.ID_HOME_TEAM to TEXT,
                Favorite.ID_AWAY_TEAM to TEXT,
                Favorite.DATE to TEXT,
                Favorite.TIME to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(TeamFavorite.TABLE_NAME, true)
    }
}

// Access
val Context.database: MyDatabaseHelper get() = MyDatabaseHelper.getInstance(applicationContext)