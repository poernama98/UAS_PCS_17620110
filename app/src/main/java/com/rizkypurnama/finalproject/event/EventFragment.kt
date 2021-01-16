package com.rizkypurnama.finalproject.event

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.alfianyusufabdullah.initViewPager

import com.rizkypurnama.finalproject.event.last.AdapterLastEvent
import com.rizkypurnama.finalproject.event.next.NextEventFragment
import com.rizkypurnama.finalproject.event.last.LastEventFragment
import com.rizkypurnama.finalproject.event.search.EventPresenter
import com.rizkypurnama.finalproject.event.search.EventView
import com.rizkypurnama.finalproject.eventdetail.EventDetailActivity
import com.rizkypurnama.finalproject.model.Event
import com.google.gson.Gson
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.api.ApiRepository
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class EventFragment : Fragment(), EventView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapterEvent: AdapterLastEvent
    private var event: MutableList<Event> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var tabLayout: TabLayout
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.event)

        recyclerView = find(R.id.list_event)
        swipeRefresh = find(R.id.swipe_event)
        swipeRefresh.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        tabLayout = find(R.id.tab_layout)
        linearLayout = find(R.id.linear)

        swipeRefresh.visibility = View.GONE

        swipeRefresh.onRefresh {
            swipeRefresh.visibility = View.GONE

            linearLayout.visibility = View.VISIBLE
        }

        initViewPager(R.id.viewpager, tabLayout) {
            addPages("NEXT", NextEventFragment())
            addPages("LAST", LastEventFragment())
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
                swipeRefresh.visibility = View.VISIBLE

                linearLayout.visibility = View.GONE

                loadData()

                presenter.getEventList(newText)
                return true
            }
        })
    }

    private fun loadData() {
        adapterEvent = AdapterLastEvent(ctx, event) {
            startActivity<EventDetailActivity>(
                    "IdEvent" to it.IdEvent,
                    "League" to it.League,
                    "HomeTeam" to it.HomeTeam,
                    "AwayTeam" to it.AwayTeam,
                    "HomeScore" to it.HomeScore,
                    "AwayScore" to it.AwayScore,
                    "HomeGoalDetails" to it.HomeGoalDetails,
                    "HomeRedCards" to it.HomeRedCards,
                    "HomeYellowCards" to it.HomeYellowCards,
                    "HomeLineupGoalkeeper" to it.HomeLineupGoalkeeper,
                    "HomeLineupDefense" to it.HomeLineupDefense,
                    "HomeLineupMidfield" to it.HomeLineupMidfield,
                    "HomeLineupForward" to it.HomeLineupForward,
                    "HomeLineupSubstitutes" to it.HomeLineupSubstitutes,
                    "AwayGoalDetails" to it.AwayGoalDetails,
                    "AwayRedCards" to it.AwayRedCards,
                    "AwayYellowCards" to it.AwayYellowCards,
                    "AwayLineupGoalkeeper" to it.AwayLineupGoalkeeper,
                    "AwayLineupDefense" to it.AwayLineupDefense,
                    "AwayLineupMidfield" to it.AwayLineupMidfield,
                    "AwayLineupForward" to it.AwayLineupForward,
                    "AwayLineupSubstitutes" to it.AwayLineupSubstitutes,
                    "HomeShots" to it.HomeShots,
                    "AwayShots" to it.AwayShots,
                    "IdHomeTeam" to it.IdHomeTeam,
                    "IdAwayTeam" to it.IdAwayTeam,
                    "CodeBack" to "1",
                    "Date" to it.Date,
                    "Time" to it.Time)
        }
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.adapter = adapterEvent

        val request = ApiRepository()
        val gson = Gson()
        presenter = EventPresenter(this, request, gson)
    }

    override fun showEventList(data: List<Event>) {
        event.clear()
        event.addAll(data)
        adapterEvent.notifyDataSetChanged()
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }
}
