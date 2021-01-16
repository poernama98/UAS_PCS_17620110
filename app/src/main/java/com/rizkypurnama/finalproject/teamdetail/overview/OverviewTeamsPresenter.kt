package com.rizkypurnama.finalproject.teamdetail.overview

import com.rizkypurnama.finalproject.model.ResponseTeam
import com.google.gson.Gson
import com.rizkypurnama.finalproject.api.ApiRepository
import com.rizkypurnama.finalproject.api.TheSportDBApi
import com.rizkypurnama.finalproject.until.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class OverviewTeamsPresenter(private val view: OverviewTeamsView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getOverview(nameTeams: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsOverview(nameTeams)),
                        ResponseTeam::class.java)
            }
            view.showOverview(data.await().teams)
            view.hideLoading()
        }
    }
}