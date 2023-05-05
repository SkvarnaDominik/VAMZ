package com.example.semestralnapraca_skvarna.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.semestralnapraca_skvarna.database.GameRecord
import com.example.semestralnapraca_skvarna.database.GameRecordDao
import com.example.semestralnapraca_skvarna.database.GameRecordDatabase
import com.example.semestralnapraca_skvarna.database.GameRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameRecordViewModel(application: Application): AndroidViewModel(application) {

    private val getAllData: LiveData<List<GameRecord>>
    private val repository: GameRecordRepository


    init {
        val gameRecordDao = GameRecordDatabase.getDatabase(application).gameRecordDao()
        repository = GameRecordRepository(gameRecordDao)
        getAllData = repository.getAllData
    }

    fun addGameRecord(gameRecord: GameRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGameRecord(gameRecord)
        }
    }
}