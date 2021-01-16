package com.rizkypurnama.finalproject.teamdetail.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.rizkypurnama.finalproject.playerdetail.DetailPlayerActivity
import com.rizkypurnama.finalproject.model.Player
import com.google.gson.Gson
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.api.ApiRepository
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.startActivity


class PlayerFragment : Fragment(), PlayerView {
    private lateinit var idTeam: String
    private lateinit var presenter: PlayerPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private val player: MutableList<Player> = mutableListOf()
    private lateinit var adapterPlayer: AdapterPlayer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = find(R.id.progress)
        recyclerView = find(R.id.recyclerView)

        idTeam = arguments?.getString("idTeam").toString()

        loadPlayer()

        presenter.getPlayer(idTeam)
    }

    companion object {
        fun newInstance(idTeam: String): PlayerFragment {
            val playerFragment = PlayerFragment()
            val bundle = Bundle()
            bundle.putString("idTeam", idTeam)
            playerFragment.arguments = bundle
            return playerFragment
        }
    }

    private fun loadPlayer() {
        adapterPlayer = AdapterPlayer(ctx, player) {
            startActivity<DetailPlayerActivity>("ImagePlayer" to "${it.Fanart2}",
                    "Heigth" to "${it.Height}",
                    "Weigth" to "${it.Weight}",
                    "Position" to "${it.Position}",
                    "Description" to "${it.Description}")
        }
        recyclerView.layoutManager = GridLayoutManager(ctx, 2)
        recyclerView.adapter = adapterPlayer

        val repository = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, repository, gson)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showEventList(data: List<Player>) {
        player.clear()
        player.addAll(data)
        adapterPlayer.notifyDataSetChanged()
    }
}
