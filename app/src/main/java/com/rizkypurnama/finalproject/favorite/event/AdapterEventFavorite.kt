package com.rizkypurnama.finalproject.favorite.event

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rizkypurnama.finalproject.R
import com.rizkypurnama.finalproject.db.Favorite
import kotlinx.android.synthetic.main.layout_list_last_event.view.*
import java.text.SimpleDateFormat
import java.util.*

class AdapterEventFavorite(private val context: Context, private val event: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<AdapterEventFavorite.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_last_event, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(event[position], listener)
    }

    override fun getItemCount(): Int = event.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(event: Favorite, listener: (Favorite) -> Unit) {
            changeDate(event.date)
            itemView.time.text = event.time
            itemView.homeTeams.text = event.homeTeam
            itemView.homeScore.text = event.homeScore
            itemView.awayTeams.text = event.awayTeam
            itemView.awayScore.text = event.awayScore
            itemView.setOnClickListener {
                listener(event)
            }
        }

        private fun changeDate(date: String?) {
            val input = "yyyy-MM-dd"
            val output = "EEE, dd MMM yyy"

            val inputFormat = SimpleDateFormat(input, Locale.ENGLISH)
            val outputFormat = SimpleDateFormat(output, Locale.ENGLISH)

            val dat = inputFormat.parse(date)
            val str = outputFormat.format(dat)

            itemView.date.text = str
        }
    }
}

