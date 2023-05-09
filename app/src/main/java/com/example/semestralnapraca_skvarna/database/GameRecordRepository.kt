package com.example.semestralnapraca_skvarna.database

import androidx.lifecycle.LiveData

class GameRecordRepository(private val gameRecordDao: GameRecordDao) {

    val getAllData: LiveData<List<GameRecord>> = gameRecordDao.getAllData()

    suspend fun addGameRecord(gameRecord: GameRecord) { //Suspend (vykonáva sa v inom vlákne ako hlavný beh programu) metóda, ktorá pridá záznam do databázy.
        gameRecordDao.addGameRecord(gameRecord)
    }

    suspend fun removeAllData() { //Odstránenie všetkých záznamov z databázy
        gameRecordDao.removeAllData()
    }
}