package com.example.semestralnapraca_skvarna.recycleview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.database.GameRecord

class ScoreAdapter: RecyclerView.Adapter<ScoreAdapter.MyViewHolder>() {

    private var gameRecordList = emptyList<GameRecord>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val profilePicture : ImageView = itemView.findViewById(R.id.ivProfilePicture)
        val username: TextView = itemView.findViewById(R.id.tvUsername)
        val game : TextView = itemView.findViewById(R.id.tvGame)
        val gameDifficulty: TextView = itemView.findViewById(R.id.tvGameDifficulty)
        val score: TextView = itemView.findViewById(R.id.tvScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.game_record, parent, false))
    }

    override fun getItemCount(): Int {
        return gameRecordList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = gameRecordList[position]
        holder.profilePicture.setImageResource(currentItem.profilePicture)
        holder.username.text = currentItem.username
        holder.game.text = currentItem.game
        holder.gameDifficulty.text = currentItem.gameDifficulty
        holder.score.text = currentItem.score.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setGameRecordData(gameRecord: List<GameRecord>) {
        this.gameRecordList = gameRecord
        notifyDataSetChanged()
    }
}