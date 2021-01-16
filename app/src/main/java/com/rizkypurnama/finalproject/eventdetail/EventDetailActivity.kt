package com.rizkypurnama.finalproject.eventdetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.rizkypurnama.finalproject.model.Teams
import com.google.gson.Gson
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.api.ApiRepository
import com.rizkypurnama.finalproject.db.Favorite
import com.rizkypurnama.finalproject.db.database
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.toast
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.delete

class EventDetailActivity : AppCompatActivity(), DetailEventView {

    private lateinit var idEvent: String
    private var league: String? = ""
    private var homeScore: String? = ""
    private var awayScore: String? = ""
    private var homeTeam: String? = ""
    private var awayTeam: String? = ""
    private var homeGoal: String? = ""
    private var awayGoal: String? = ""
    private var homeYellowCard: String? = ""
    private var awayYellowCard: String? = ""
    private var homeRedCard: String? = ""
    private var awayRedCard: String? = ""
    private var homeShots: String? = ""
    private var awayShots: String? = ""
    private var homeGk: String? = ""
    private var awayGk: String? = ""
    private var homeDefense: String? = ""
    private var awayDefense: String? = ""
    private var homeMidfield: String? = ""
    private var awayMidfield: String? = ""
    private var homeForward: String? = ""
    private var awayForward: String? = ""
    private var homeSubtitutes: String? = ""
    private var awaySubtitutes: String? = ""
    private var IdHomeTeam: String? = ""
    private var IdAwayTeam: String? = ""
    private var codeBack: String? = ""
    private var date: String? = ""
    private var time: String? = ""
    private lateinit var presenter: DetailEventPresenter
    private lateinit var team: Teams
    private var inFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        val intent = intent
        intent?.let {
            idEvent = it.getStringExtra("IdEvent")
            league = it.getStringExtra("League")
            homeScore = it.getStringExtra("HomeScore")
            awayScore = it.getStringExtra("AwayScore")
            homeTeam = it.getStringExtra("HomeTeam")
            awayTeam = it.getStringExtra("AwayTeam")
            homeGoal = it.getStringExtra("HomeGoalDetails")
            awayGoal = it.getStringExtra("AwayGoalDetails")
            homeYellowCard = it.getStringExtra("HomeYellowCards")
            awayYellowCard = it.getStringExtra("AwayYellowCards")
            homeRedCard = it.getStringExtra("HomeRedCards")
            awayRedCard = it.getStringExtra("AwayRedCards")
            homeShots = it.getStringExtra("HomeShots")
            awayShots = it.getStringExtra("AwayShots")
            homeGk = it.getStringExtra("HomeLineupGoalkeeper")
            awayGk = it.getStringExtra("AwayLineupGoalkeeper")
            homeDefense = it.getStringExtra("HomeLineupDefense")
            awayDefense = it.getStringExtra("AwayLineupDefense")
            homeMidfield = it.getStringExtra("HomeLineupMidfield")
            awayMidfield = it.getStringExtra("AwayLineupMidfield")
            homeForward = it.getStringExtra("HomeLineupForward")
            awayForward = it.getStringExtra("AwayLineupForward")
            homeSubtitutes = it.getStringExtra("HomeLineupSubstitutes")
            awaySubtitutes = it.getStringExtra("AwayLineupSubstitutes")
            IdHomeTeam = it.getStringExtra("IdHomeTeam")
            IdAwayTeam = it.getStringExtra("IdAwayTeam")
            codeBack = it.getStringExtra("CodeBack")
            date = it.getStringExtra("Date")
            time = it.getStringExtra("Time")
        }

        showFavorite()
        loadData()

        presenter.getTeamHome(IdHomeTeam)
        presenter.getTeamAway(IdAwayTeam)

        showData()

        faforite.setOnClickListener {
            if (inFavorite) {
                deleteFavorite()
                faforite.setImageResource(R.drawable.ic_no_fav)
            } else {
                saveFovirite()
                faforite.setImageResource(R.drawable.ic_yes_fav)
                inFavorite = true
            }
        }
    }

    private fun loadData() {
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailEventPresenter(this@EventDetailActivity, request, gson)
    }

    private fun showData() {
        home_score.text = homeScore
        away_score.text = awayScore
        home_team.text = homeTeam
        away_team.text = awayTeam

        if (homeGoal == null && awayGoal == null) {
            home_goal.text = getString(R.string.data_kosong)
            away_goal.text = getString(R.string.data_kosong)
        } else if (homeGoal.equals("") && awayGoal.equals("")) {
            home_goal.text = "-"
            away_goal.text = "-"
        } else if (awayGoal.equals("")) {
            away_goal.text = "-"
            home_goal.text = homeGoal
        } else if (homeGoal.equals("")) {
            home_goal.text = "-"
            away_goal.text = awayGoal
        } else {
            home_goal.text = homeGoal
            away_goal.text = awayGoal
        }

        if (homeYellowCard == null && awayYellowCard == null) {
            home_yellow_card.text = getString(R.string.data_kosong)
            away_yellow_card.text = getString(R.string.data_kosong)
        } else if (homeYellowCard.equals("") && awayYellowCard.equals("")) {
            home_yellow_card.text = "-"
            away_yellow_card.text = "-"
        } else if (awayYellowCard.equals("")) {
            away_yellow_card.text = "-"
            home_yellow_card.text = homeYellowCard
        } else if (homeYellowCard.equals("")) {
            home_yellow_card.text = "-"
            away_yellow_card.text = awayYellowCard
        } else {
            home_yellow_card.text = homeYellowCard
            away_yellow_card.text = awayYellowCard
        }

        if (homeRedCard == null && awayRedCard == null) {
            home_red_card.text = getString(R.string.data_kosong)
            away_red_card.text = getString(R.string.data_kosong)
        } else if (homeRedCard.equals("") && awayRedCard.equals("")) {
            home_red_card.text = "-"
            away_red_card.text = "-"
        } else if (awayRedCard.equals("")) {
            away_red_card.text = "-"
            home_red_card.text = homeRedCard
        } else if (homeRedCard.equals("")) {
            home_red_card.text = "-"
            away_red_card.text = awayRedCard
        } else {
            home_red_card.text = homeRedCard
            away_red_card.text = awayRedCard
        }

        if (homeShots == null && awayRedCard == null) {
            home_shots.text = getString(R.string.data_kosong)
            away_shots.text = getString(R.string.data_kosong)
        } else if (homeShots.equals("") && awayRedCard.equals("")) {
            home_shots.text = "-"
            away_shots.text = "-"
        } else if (awayShots.equals("")) {
            away_shots.text = "-"
            home_shots.text = homeShots
        } else if (homeShots.equals("")) {
            home_shots.text = "-"
            away_shots.text = awayShots
        } else {
            home_shots.text = homeShots
            away_shots.text = awayShots
        }

        if (homeGk == null && awayGk == null) {
            home_gk.text = getString(R.string.data_kosong)
            away_gk.text = getString(R.string.data_kosong)
        } else {
            home_gk.text = homeGk
            away_gk.text = awayGk
        }

        if (homeDefense == null && awayDefense == null) {
            home_defense.text = getString(R.string.data_kosong)
            away_defense.text = getString(R.string.data_kosong)
        } else {
            home_defense.text = homeDefense
            away_defense.text = awayDefense
        }

        if (homeMidfield == null && awayMidfield == null) {
            home_midfield.text = getString(R.string.data_kosong)
            away_midfield.text = getString(R.string.data_kosong)
        } else {
            home_midfield.text = homeMidfield
            away_midfield.text = awayMidfield
        }

        if (homeForward == null && awayForward == null) {
            home_fordward.text = getString(R.string.data_kosong)
            away_fordward.text = getString(R.string.data_kosong)
        } else {
            home_fordward.text = homeForward
            away_fordward.text = awayForward
        }

        if (homeSubtitutes == null && awaySubtitutes == null) {
            home_substitutes.text = getString(R.string.data_kosong)
            away_substitutes.text = getString(R.string.data_kosong)
        } else {
            home_substitutes.text = homeSubtitutes
            away_substitutes.text = awaySubtitutes
        }


    }

    private fun saveFovirite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                        Favorite.ID_EVENT to idEvent,
                        Favorite.LEAGUE to league,
                        Favorite.HOME_SCORE to homeScore,
                        Favorite.AWAY_SCORE to awayScore,
                        Favorite.HOME_TEAM to homeTeam,
                        Favorite.AWAY_TEAM to awayTeam,
                        Favorite.HOME_GOAL to homeGoal,
                        Favorite.AWAY_GOAL to awayGoal,
                        Favorite.HOME_YELLOW_CARD to homeYellowCard,
                        Favorite.AWAY_YELLOW_CARD to awayYellowCard,
                        Favorite.HOME_RED_CARD to homeRedCard,
                        Favorite.AWAY_RED_CARD to awayRedCard,
                        Favorite.HOME_SHOTS to homeShots,
                        Favorite.AWAY_SHOTS to awayShots,
                        Favorite.HOME_GK to homeGk,
                        Favorite.AWAY_GK to awayGk,
                        Favorite.HOME_DEFENSE to homeDefense,
                        Favorite.AWAY_DEFENSE to awayDefense,
                        Favorite.HOME_MIDFIELD to homeMidfield,
                        Favorite.AWAY_MIDFIELD to awayMidfield,
                        Favorite.HOME_FORWARD to homeForward,
                        Favorite.AWAY_FORWARD to awayForward,
                        Favorite.HOME_SUBTITUTES to homeSubtitutes,
                        Favorite.AWAY_SUBTITUTES to awaySubtitutes,
                        Favorite.ID_HOME_TEAM to IdHomeTeam,
                        Favorite.ID_AWAY_TEAM to IdAwayTeam,
                        Favorite.DATE to date,
                        Favorite.TIME to time)
            }
            Snackbar.make(home_substitutes, getString(R.string.savefavorite), Snackbar.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Log.d("Gagal ", e.localizedMessage)
        }
    }

    private fun deleteFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(ID_EVENT = {id})",
                        "id" to idEvent)
            }
            Snackbar.make(home_substitutes, getString(R.string.deletefavorite), Snackbar.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Log.d("Gagal ", e.localizedMessage)
        }
    }

    private fun showFavorite() {
        try {
            database.use {
                val result = select(Favorite.TABLE_FAVORITE)
                        .whereArgs("(ID_EVENT = {id})",
                                "id" to idEvent)
                val favorite = result.parseList(classParser<Favorite>())
                if (!favorite.isEmpty()) {
                    faforite.setImageResource(R.drawable.ic_yes_fav)
                    inFavorite = true
                } else {
                    faforite.setImageResource(R.drawable.ic_no_fav)
                }
            }
        } catch (e: SQLiteConstraintException) {
            toast("Opss " + e.localizedMessage)
        }
    }

    override fun showLoading() {
        home_image_progres.visibility = View.VISIBLE
        away_image_progres.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        home_image_progres.visibility = View.GONE
        away_image_progres.visibility = View.GONE
    }

    override fun showTeamHome(data: List<Teams>) {
        team = Teams(data[0].teamBadge)
        Glide.with(baseContext).load(data[0].teamBadge).into(home_image)
    }

    override fun showTeamAway(data: List<Teams>) {
        team = Teams(data[0].teamBadge)
        Glide.with(baseContext).load(data[0].teamBadge).into(away_image)
    }
}
