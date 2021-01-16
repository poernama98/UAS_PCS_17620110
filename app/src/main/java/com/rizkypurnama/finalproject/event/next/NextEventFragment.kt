package com.rizkypurnama.finalproject.event.next

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.rizkypurnama.finalproject.eventdetail.EventDetailActivity
import com.rizkypurnama.finalproject.model.Event
import com.google.gson.Gson
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.api.ApiRepository
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class NextEventFragment : Fragment(), NextEventView {

    private lateinit var spinner: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapterEvent: AdapterNextEvent
    private var event: MutableList<Event> = mutableListOf()
    private lateinit var presenter: NextEventPresenter
    private lateinit var idLeague: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = find(R.id.spinner)
        recyclerView = find(R.id.list_next_event)
        swipeRefresh = find(R.id.swipe_next_event)
        swipeRefresh.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        loadData()

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0) {
                    idLeague = "4328"
                } else if (p2 == 1) {
                    idLeague = "4335"
                } else if (p2 == 2) {
                    idLeague = "4331"
                } else if (p2 == 3) {
                    idLeague = "4332"
                } else if (p2 == 4) {
                    idLeague = "4334"
                }

                presenter.getEventList(idLeague)
            }
        }

        swipeRefresh.onRefresh {
            presenter.getEventList(idLeague)
        }
    }

    private fun loadData() {
        adapterEvent = AdapterNextEvent(ctx, event) {
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
        presenter = NextEventPresenter(this, request, gson)
    }


    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showEventList(data: List<Event>) {
        event.clear()
        event.addAll(data)
        adapterEvent.notifyDataSetChanged()
    }
}
