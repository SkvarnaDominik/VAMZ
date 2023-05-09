package com.example.semestralnapraca_skvarna.recycleview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semestralnapraca_skvarna.R
import com.example.semestralnapraca_skvarna.database.GameRecord

class ScoreAdapter: RecyclerView.Adapter<ScoreAdapter.GameRecordViewHolder>() {

    private val profilePictureResources: IntArray = intArrayOf(
        R.drawable.profile_1,
        R.drawable.profile_2,
        R.drawable.profile_3,
        R.drawable.profile_4
    )

    private var gameRecordList = listOf<GameRecord>()

    class GameRecordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val profilePicture : ImageView = itemView.findViewById(R.id.ivProfilePicture)
        val username: TextView = itemView.findViewById(R.id.tvUsername)
        val game : TextView = itemView.findViewById(R.id.tvGame)
        val gameDifficulty: TextView = itemView.findViewById(R.id.tvGameDifficulty)
        val score: TextView = itemView.findViewById(R.id.tvScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameRecordViewHolder {
        return GameRecordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.game_record, parent, false))
    }

    override fun getItemCount(): Int {
        return gameRecordList.size
    }

    override fun onBindViewHolder(holder: GameRecordViewHolder, position: Int) {
        val currentItem = gameRecordList[position]
        holder.profilePicture.setImageResource(profilePictureResources[currentItem.profilePicture])
        holder.profilePicture.layoutParams.height = 300
        holder.profilePicture.layoutParams.width = 300
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