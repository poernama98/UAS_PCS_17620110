package com.rizkypurnama.finalproject.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.event.EventFragment
import com.rizkypurnama.finalproject.favorite.FavoriteFragment
import com.rizkypurnama.finalproject.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    private lateinit var fragment: Fragment
    private var viewIsAtHome: Boolean = false

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val id = item.itemId

        loadFragment(id)

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        loadFragment(R.id.navigation_event)
    }

    private fun loadFragment(itemId: Int) {
        when (itemId) {
            R.id.navigation_event -> {
                fragment = EventFragment()
                viewIsAtHome = true

            }
            R.id.navigation_teams -> {
                fragment = TeamsFragment()
                viewIsAtHome = false
            }
            R.id.navigation_favorite -> {
                fragment = FavoriteFragment()
                viewIsAtHome = false
            }
        }

        val fragmentManager = supportFragmentManager
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.frame, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!viewIsAtHome) {
                startActivity(intentFor<MainActivity>().noAnimation())
                finish()
            } else {
                alert(getString(R.string.quit)) {
                    yesButton {
                        finish()
                    }
                    noButton { }
                }.show()
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
