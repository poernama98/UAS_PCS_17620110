package com.rizkypurnama.finalproject.event.next

import com.rizkypurnama.finalproject.model.ResponseEvent
import com.rizkypurnama.finalproject.until.CoroutineContextProvider
import com.google.gson.Gson
import com.rizkypurnama.finalproject.api.ApiRepository
import com.rizkypurnama.finalproject.api.TheSportDBApi
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextEventPresenter(private val view: NextEventView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getEventList(league: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextEvent(league)),
                        ResponseEvent::class.java)
            }
            view.showEventList(data.await().events)
            view.hideLoading()
        }
    }
}