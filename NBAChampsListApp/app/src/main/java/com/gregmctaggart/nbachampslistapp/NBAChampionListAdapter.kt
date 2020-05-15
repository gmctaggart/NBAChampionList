package com.gregmctaggart.nbachampslistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView

class NBAChampionListAdapter : RecyclerView.Adapter<NBAChampionListAdapter.ViewHolder>() {

    interface DataChangedCallback {
        fun onDataChanged()
    }

    private val items = mutableListOf<NBAChampion>()

    @VisibleForTesting
    fun getItemData() = items

    @VisibleForTesting
    var dataChangedCallback = object : DataChangedCallback {
        override fun onDataChanged() {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nba_champion_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items: List<NBAChampion>) {
        this.items.clear()
        this.items.addAll(items)

        dataChangedCallback.onDataChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val teamName: TextView = itemView.findViewById(R.id.nba_team_name_tv)
        private val yearWon: TextView = itemView.findViewById(R.id.year_won_tv)

        fun bind(champion: NBAChampion) {
            teamName.text = champion.teamName
            yearWon.text = champion.year.toString()
        }
    }
}