package com.rizkypurnama.finalproject.teamdetail.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide

import com.rizkypurnama.finalproject.model.Teams
import com.google.gson.Gson
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.api.ApiRepository
import org.jetbrains.anko.support.v4.find


class OverviewFragment : Fragment(), OverviewTeamsView {
    private lateinit var presenter: OverviewTeamsPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var linearLayout: LinearLayout
    private lateinit var teams: Teams
    private lateinit var description: TextView
    private lateinit var manager: TextView
    private lateinit var stadiumdescription: TextView
    private lateinit var jersey: ImageView
    private lateinit var idTeam: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = find(R.id.progress)
        linearLayout = find(R.id.linear)
        description = find(R.id.description)
        manager = find(R.id.nameManager)
        stadiumdescription = find(R.id.stadiumDescription)
        jersey = find(R.id.jersey)

        idTeam = arguments?.getString("idTeam").toString()

        val request = ApiRepository()
        val gson = Gson()
        presenter = OverviewTeamsPresenter(this, request, gson)
        presenter.getOverview(idTeam)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        linearLayout.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        linearLayout.visibility = View.VISIBLE
    }

    override fun showOverview(data: List<Teams>) {
        teams = Teams(data[0].teamDescription,
                data[0].manager,
                data[0].stadiumDescription,
                data[0].jersey)
        description.text = data[0].teamDescription
        manager.text = data[0].manager
        stadiumdescription.text = data[0].stadiumDescription
        Glide.with(this).load(data[0].jersey).into(jersey)
    }

    companion object {
        fun newInstance(idTeam: String): OverviewFragment {
            val overviewFragment = OverviewFragment()
            val args = Bundle()
            args.putString("idTeam", idTeam)
            overviewFragment.arguments = args
            return overviewFragment
        }
    }
}
