package com.rizkypurnama.finalproject.teamdetail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.alfianyusufabdullah.initViewPager
import com.bumptech.glide.Glide
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.db.TeamFavorite
import com.rizkypurnama.finalproject.db.database
import com.rizkypurnama.finalproject.teamdetail.overview.OverviewFragment
import com.rizkypurnama.finalproject.teamdetail.player.PlayerFragment
import kotlinx.android.synthetic.main.fragment_event.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.find

class DetailTeamActivity : AppCompatActivity() {
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var imageTeams: ImageView
    private lateinit var imageLogoTeams: ImageView
    private lateinit var nameTeams: TextView
    private lateinit var idTeam: String
    private lateinit var teamBadge: String
    private lateinit var teamFenart: String
    private lateinit var teamName: String
    private lateinit var tabLayout: TabLayout
    private lateinit var favorite: ImageView
    private var inFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        collapsingToolbarLayout = find(R.id.toolbar_layout)
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(baseContext, android.R.color.transparent))

        imageTeams = find(R.id.imageTeams)
        imageLogoTeams = find(R.id.imageLogoTeams)
        nameTeams = find(R.id.nameTeams)
        tabLayout = find(R.id.tab_layout)
        favorite = find(R.id.favorite)

        val intent = intent
        intent?.let {
            idTeam = it.getStringExtra("TeamId")
            teamBadge = it.getStringExtra("TeamBadge")
            teamFenart = it.getStringExtra("TeamFenart")
            teamName = it.getStringExtra("TeamName")
        }

        Glide.with(baseContext).load(teamFenart).into(imageTeams)
        Glide.with(baseContext).load(teamBadge).into(imageLogoTeams)
        nameTeams.text = teamName

        checkTeamFavorite(idTeam)

        favorite.setOnClickListener {
            if (inFavorite) {
                deleteTeamFavorite(idTeam)
                favorite.setImageResource(R.drawable.ic_no_fav)
            } else {
                saveTeamFavorite(idTeam, teamBadge, teamFenart, teamName)
                favorite.setImageResource(R.drawable.ic_yes_fav)
                inFavorite = true
            }
        }

        initViewPager(R.id.viewpager, tabLayout) {
            addPages("OVERVIEW", OverviewFragment.newInstance(idTeam))
            addPages("PLAYER", PlayerFragment.newInstance(idTeam))
        }
    }

    private fun saveTeamFavorite(idTeam: String, teamBadge: String, teamFenart: String, teamName: String) {
        try {
            database.use {
                insert(
                    TeamFavorite.TABLE_NAME,
                        TeamFavorite.ID_TEAM to idTeam,
                        TeamFavorite.TEAM_BADGE to teamBadge,
                        TeamFavorite.TEAM_FENART to teamFenart,
                        TeamFavorite.TEAM_NAME to teamName)
            }
            Snackbar.make(viewpager, getString(R.string.savefavorite), Snackbar.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Log.d(getString(R.string.gagal), e.localizedMessage)
        }
    }

    private fun deleteTeamFavorite(idTeam: String) {
        try {
            database.use {
                delete(TeamFavorite.TABLE_NAME, "(ID_TEAM = {id})",
                        "id" to idTeam)
            }
            Snackbar.make(viewpager, getString(R.string.deletefavorite), Snackbar.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Log.d(getString(R.string.gagal), e.localizedMessage)
        }
    }

    private fun checkTeamFavorite(idTeam: String) {
        try {
            database.use {
                val result = select(TeamFavorite.TABLE_NAME)
                        .whereArgs("(ID_TEAM = {id})", "id" to idTeam)
                val fav = result.parseList(classParser<TeamFavorite>())
                if (!fav.isEmpty()) {
                    favorite.setImageResource(R.drawable.ic_yes_fav)
                    inFavorite = true
                } else {
                    favorite.setImageResource(R.drawable.ic_no_fav)
                }
            }
        } catch (e: SQLiteConstraintException) {
            Log.d(getString(R.string.gagal), e.localizedMessage)
        }
    }
}
