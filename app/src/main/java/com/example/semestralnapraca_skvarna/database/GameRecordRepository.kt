package com.example.semestralnapraca_skvarna.database

import androidx.lifecycle.LiveData

class GameRecordRepository(private val gameRecordDao: GameRecordDao) {

    val getAllData: LiveData<List<GameRecord>> = gameRecordDao.getAllData()

    suspend fun addGameRecord(gameRecord: GameRecord) {
        gameRecordDao.addGameRecord(gameRecord)
    }
}