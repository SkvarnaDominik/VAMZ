package com.example.semestralnapraca_skvarna.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameRecordDao {

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun addGameRecord(gameRecord: GameRecord)

    @Query("SELECT * FROM game_records ORDER BY id ASC")
    fun getAllData(): LiveData<List<GameRecord>>

    @Query("DELETE FROM game_records ")
    fun removeAllData()

}