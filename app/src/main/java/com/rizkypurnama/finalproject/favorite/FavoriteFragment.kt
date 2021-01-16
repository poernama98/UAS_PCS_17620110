package com.rizkypurnama.finalproject.favorite

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfianyusufabdullah.initViewPager
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.favorite.event.EventFavoriteFragment
import com.rizkypurnama.finalproject.favorite.teams.TeamsFavoriteFragment
import org.jetbrains.anko.support.v4.find

class FavoriteFragment : Fragment() {
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.fav)

        tabLayout = find(R.id.tab_layout)

        initViewPager(R.id.viewpager, tabLayout) {
            addPages("EVENT", EventFavoriteFragment())
            addPages("TEAMS", TeamsFavoriteFragment())
        }
    }
}
