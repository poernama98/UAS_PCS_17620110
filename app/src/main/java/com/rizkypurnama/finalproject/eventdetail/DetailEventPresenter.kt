package com.rizkypurnama.finalproject.eventdetail

import com.rizkypurnama.finalproject.model.ResponseTeam
import com.rizkypurnama.finalproject.until.CoroutineContextProvider
import com.google.gson.Gson
import com.rizkypurnama.finalproject.api.ApiRepository
import com.rizkypurnama.finalproject.api.TheSportDBApi
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailEventPresenter(private val view: DetailEventView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamHome(team: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailEvent(team)),
                        ResponseTeam::class.java)
            }
            view.showTeamHome(data.await().teams)
            view.hideLoading()
        }
    }

    fun getTeamAway(team: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailEvent(team)),
                        ResponseTeam::class.java)
            }
            view.showTeamAway(data.await().teams)
            view.hideLoading()
        }
    }
}