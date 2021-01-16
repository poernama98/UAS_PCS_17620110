package com.rizkypurnama.finalproject.favorite.teams

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.db.TeamFavorite
import kotlinx.android.synthetic.main.layout_list_teams.view.*

class AdapterTeamsFavorite(private val context: Context, private val teams: List<TeamFavorite>, private val listener: (TeamFavorite) -> Unit)
    : RecyclerView.Adapter<AdapterTeamsFavorite.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_teams, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(context, teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(context: Context, teams: TeamFavorite, listener: (TeamFavorite) -> Unit) {
            itemView.nameTeams.text = teams.teamName
            Glide.with(context).load(teams.teamBadge).listener(object : RequestListener<Drawable> {
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    itemView.progress.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    itemView.progress.visibility = View.GONE
                    return false
                }

            }).into(itemView.imageTeams)

            itemView.setOnClickListener {
                listener(teams)
            }
        }
    }
}

