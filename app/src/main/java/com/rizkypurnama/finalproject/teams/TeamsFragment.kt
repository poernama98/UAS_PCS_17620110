package com.rizkypurnama.finalproject.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.rizkypurnama.finalproject.teamdetail.DetailTeamActivity
import com.rizkypurnama.finalproject.model.Teams
import com.rizkypurnama.finalproject.teams.search.TeamsSearchPresenter
import com.rizkypurnama.finalproject.teams.search.TeamsSearchView
import com.google.gson.Gson
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.api.ApiRepository
import org.jetbrains.anko.support.v4.*

class TeamsFragment : Fragment(), TeamsView, TeamsSearchView {

    private lateinit var spinner: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapterTeams: AdapterTeams
    private val teams: MutableList<Teams> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var presenterSearch: TeamsSearchPresenter
    private lateinit var leagueName: String
    private lateinit var cardView: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.team)

        spinner = find(R.id.spinner)
        recyclerView = find(R.id.recyclerView)
        swipeRefreshLayout = find(R.id.swipe)
        cardView = find(R.id.card)

        loadTeams()

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                leagueName = spinner.selectedItem.toString()

                presenter.getEventList(leagueName)
            }
        }

        swipeRefreshLayout.onRefresh {
            cardView.visibility = View.VISIBLE

            presenter.getEventList(leagueName)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)

        activity?.menuInflater?.inflate(R.menu.main, menu)
        val mSearchMenuItem = menu?.findItem(R.id.search)
        val searchView = mSearchMenuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cardView.visibility = View.GONE

                loadTeamsSearch()

                presenterSearch.getEventList(newText)
                return true
            }
        })
    }

    private fun loadTeams() {
        adapterTeams = AdapterTeams(ctx, teams) {
            startActivity<DetailTeamActivity>("TeamId" to "${it.teamId}",
                    "TeamBadge" to "${it.teamBadge}",
                    "TeamFenart" to "${it.teamFenart1}",
                    "TeamName" to "${it.teamName}")
        }
        recyclerView.layoutManager = GridLayoutManager(ctx, 2)
        recyclerView.adapter = adapterTeams

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)
    }

    private fun loadTeamsSearch() {
        adapterTeams = AdapterTeams(ctx, teams) {
            startActivity<DetailTeamActivity>("TeamId" to "${it.teamId}",
                    "TeamBadge" to "${it.teamBadge}",
                    "TeamFenart" to "${it.teamFenart1}",
                    "TeamName" to "${it.teamName}")
        }
        recyclerView.layoutManager = GridLayoutManager(ctx, 2)
        recyclerView.adapter = adapterTeams

        val request = ApiRepository()
        val gson = Gson()
        presenterSearch = TeamsSearchPresenter(this, request, gson)
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showEventList(data: List<Teams>) {
        teams.clear()
        teams.addAll(data)
        adapterTeams.notifyDataSetChanged()
    }

    override fun showLoadingSearch() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoadingSearch() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showEventListSearch(data: List<Teams>) {
        teams.clear()
        teams.addAll(data)
        adapterTeams.notifyDataSetChanged()
    }
}
