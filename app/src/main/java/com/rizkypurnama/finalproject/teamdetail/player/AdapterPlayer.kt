package com.rizkypurnama.finalproject.teamdetail.player

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
import com.rizkypurnama.finalproject.model.Player
import kotlinx.android.synthetic.main.layout_list_player.view.*

class AdapterPlayer(private val context: Context, private val player: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<AdapterPlayer.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_player, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(context, player[position], listener)
    }

    override fun getItemCount(): Int = player.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(context: Context, player: Player, listener: (Player) -> Unit) {
            itemView.namePlayer.text = player.Player
            itemView.positionPlayer.text = player.Position
            Glide.with(context).load(player.Cutout).listener(object : RequestListener<Drawable> {
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    itemView.progress.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    itemView.progress.visibility = View.GONE
                    return false
                }

            }).into(itemView.imagePlayer)

            itemView.setOnClickListener {
                listener(player)
            }
        }
    }
}

