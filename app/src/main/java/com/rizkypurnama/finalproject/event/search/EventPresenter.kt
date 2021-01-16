package com.rizkypurnama.finalproject.event.search

import com.rizkypurnama.finalproject.model.ResponseEventSearch
import com.rizkypurnama.finalproject.until.CoroutineContextProvider
import com.google.gson.Gson
import com.rizkypurnama.finalproject.api.ApiRepository
import com.rizkypurnama.finalproject.api.TheSportDBApi
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class EventPresenter(private val view: EventView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getEventList(teams: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEventSearch(teams)),
                        ResponseEventSearch::class.java)
            }
            view.showEventList(data.await().event)
            view.hideLoading()
        }
    }
}