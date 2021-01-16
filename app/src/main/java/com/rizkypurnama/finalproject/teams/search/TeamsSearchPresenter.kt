package com.rizkypurnama.finalproject.teams.search

import com.rizkypurnama.finalproject.model.ResponseTeam
import com.google.gson.Gson
import com.rizkypurnama.finalproject.api.ApiRepository
import com.rizkypurnama.finalproject.api.TheSportDBApi
import com.rizkypurnama.finalproject.until.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsSearchPresenter(private val view: TeamsSearchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getEventList(nameTeam: String?) {
        view.showLoadingSearch()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamSearche(nameTeam)),
                        ResponseTeam::class.java)
            }
            view.showEventListSearch(data.await().teams)
            view.hideLoadingSearch()
        }
    }
}