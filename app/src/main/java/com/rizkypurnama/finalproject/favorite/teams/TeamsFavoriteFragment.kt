package com.rizkypurnama.finalproject.favorite.teams

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.db.TeamFavorite
import com.rizkypurnama.finalproject.db.database
import com.rizkypurnama.finalproject.teamdetail.DetailTeamActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class TeamsFavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapterFavorite: AdapterTeamsFavorite
    private var favorite: MutableList<TeamFavorite> = mutableListOf()
    private lateinit var empty: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams_favorite, container, false)
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

    private fun showFavorite() {
        favorite.clear()

        try {
            activity?.database?.use {
                swipeRefresh.isRefreshing = false
                val result = select(TeamFavorite.TABLE_NAME)
                val favorites = result.parseList(classParser<TeamFavorite>())
                if (favorites.isEmpty()) {
                    empty.visibility = View.VISIBLE
                } else {
                    favorite.addAll(favorites)
                }
                adapterFavorite.notifyDataSetChanged()
            }
        } catch (e: SQLiteConstraintException) {
            Log.d("Ops!", e.localizedMessage)
        }
    }

    private fun loadFavorite() {
        adapterFavorite = AdapterTeamsFavorite(ctx, favorite) {
            startActivity<DetailTeamActivity>("TeamId" to "${it.idTeam}",
                    "TeamBadge" to "${it.teamBadge}",
                    "TeamFenart" to "${it.teamFenart}",
                    "TeamName" to "${it.teamName}")

        }

        recyclerView.layoutManager = GridLayoutManager(ctx, 2)
        recyclerView.adapter = adapterFavorite
    }
}
