package com.example.semestralnapraca_skvarna.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.database.GameRecord

class SettingsAdapter: RecyclerView.Adapter<SettingsAdapter.MyViewHolder>() {

    private var gameRecordList = emptyList<GameRecord>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.game_record, parent, false))
    }

    override fun getItemCount(): Int {
        return gameRecordList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = gameRecordList[position]
        //holder.itemView.
    }
}