package com.example.semestralnapraca_skvarna.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_records")    // Dátová trieda, ktorá reprezentuje jeden záznam odohrania hry
data class GameRecord (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val profilePicture: Int,
    val username: String,
    val game : String,
    val gameDifficulty: String,
    val score: Int
)