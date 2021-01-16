package com.rizkypurnama.finalproject.event.last

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rizkypurnama.finalproject.model.Event
import com.rizkypurnama.finalproject.R
import kotlinx.android.synthetic.main.layout_list_last_event.view.*
import java.text.SimpleDateFormat
import java.util.*

class AdapterLastEvent(private val context: Context, private val event: List<Event>, private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<AdapterLastEvent.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_last_event, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(event[position], listener)
    }

    override fun getItemCount(): Int = event.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(event: Event, listener: (Event) -> Unit) {
            changeDate(event.Date)
            itemView.time.text = event.Time
            itemView.homeTeams.text = event.HomeTeam
            itemView.homeScore.text = event.HomeScore
            itemView.awayTeams.text = event.AwayTeam
            itemView.awayScore.text = event.AwayScore

            when(event.HomeTeam) {
                "Getafe" -> {
                    itemView.setBackgroundColor(Color.parseColor("#991EB3"))
                }
            }
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

