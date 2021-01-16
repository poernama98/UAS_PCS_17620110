package com.rizkypurnama.finalproject.favorite.event

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.db.Favorite
import com.rizkypurnama.finalproject.db.database

import com.rizkypurnama.finalproject.eventdetail.EventDetailActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class EventFavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapterFavorite: AdapterEventFavorite
    private var favorite: MutableList<Favorite> = mutableListOf()
    private lateinit var empty: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = find(R.id.list_event)
        swipeRefresh = find(R.id.swipe_event)
        swipeRefresh.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        empty = find(R.id.empty)

        swipeRefresh.post {
            showFavorite()
        }

        swipeRefresh.onRefresh {
            showFavorite()
        }

        loadFavorite()
    }

    private fun loadFavorite() {
        adapterFavorite = AdapterEventFavorite(ctx, favorite) {
            startActivity<EventDetailActivity>(
                    "IdEvent" to it.id_event,
                    "League" to it.league,
                    "HomeTeam" to it.homeTeam,
                    "AwayTeam" to it.awayTeam,
                    "HomeScore" to it.homeScore,
                    "AwayScore" to it.awayScore,
                    "HomeGoalDetails" to it.homeGoal,
                    "HomeRedCards" to it.homeRedCard,
                    "HomeYellowCards" to it.homeYellowCard,
                    "HomeLineupGoalkeeper" to it.homeGk,
                    "HomeLineupDefense" to it.homeDefense,
                    "HomeLineupMidfield" to it.homeMidfield,
                    "HomeLineupForward" to it.homeForward,
                    "HomeLineupSubstitutes" to it.homeSubtitutes,
                    "AwayGoalDetails" to it.awayGoal,
                    "AwayRedCards" to it.awayRedCard,
                    "AwayYellowCards" to it.awayYellowCard,
                    "AwayLineupGoalkeeper" to it.awayGk,
                    "AwayLineupDefense" to it.awayGk,
                    "AwayLineupMidfield" to it.awayMidfield,
                    "AwayLineupForward" to it.awayForward,
                    "AwayLineupSubstitutes" to it.awaySubtitutes,
                    "HomeShots" to it.homeShots,
                    "AwayShots" to it.awayShots,
                    "IdHomeTeam" to it.IdHomeTeam,
                    "IdAwayTeam" to it.IdAwayTeam,
                    "CodeBack" to "3")
        }
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.adapter = adapterFavorite
    }

    private fun showFavorite() {
        favorite.clear()

        try {
            activity?.database?.use {
                swipeRefresh.isRefreshing = false
                val result = select(Favorite.TABLE_FAVORITE)
                val favorites = result.parseList(classParser<Favorite>())
                if (favorites.isEmpty()) {
                    empty.visibility = View.VISIBLE;
                } else {
                    favorite.addAll(favorites)
                }
                adapterFavorite.notifyDataSetChanged()
            }
        } catch (e: SQLiteConstraintException) {
            Log.d("Ops!", e.localizedMessage)
        }
    }
}
