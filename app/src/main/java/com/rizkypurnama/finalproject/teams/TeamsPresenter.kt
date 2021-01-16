package com.rizkypurnama.finalproject.teams

import com.rizkypurnama.finalproject.model.ResponseTeam
import com.rizkypurnama.finalproject.until.CoroutineContextProvider
import com.google.gson.Gson
import com.rizkypurnama.finalproject.api.ApiRepository
import com.rizkypurnama.finalproject.api.TheSportDBApi
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getEventList(nameLeague: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(nameLeague)),
                        ResponseTeam::class.java)
            }
            view.showEventList(data.await().teams)
            view.hideLoading()
        }
    }
}