package com.rizkypurnama.finalproject.teamdetail.player

import com.rizkypurnama.finalproject.model.ResponsePlayer
import com.google.gson.Gson
import com.rizkypurnama.finalproject.api.ApiRepository
import com.rizkypurnama.finalproject.api.TheSportDBApi
import com.rizkypurnama.finalproject.until.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getPlayer(idTeam: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPlayer(idTeam)),
                        ResponsePlayer::class.java)
            }
            view.showEventList(data.await().player)
            view.hideLoading()
        }
    }
}