package com.example.semestralnapraca_skvarna.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameRecordDao {

    @Insert(onConflict = OnConflictStrategy.NONE) //Stratégia pokiaľ budú viaceré záznamy rovnaké. V tomto prípade sa to nijak riešiť nebude
    suspend fun addGameRecord(gameRecord: GameRecord)  //Suspend (vykonáva sa v inom vlákne ako hlavný beh programu) metóda, ktorá pridá záznam do databázy.

    @Query("SELECT * FROM game_records ORDER BY id ASC") //Načítanie všetkých záznamov z databázy
    fun getAllData(): LiveData<List<GameRecord>>

    @Query("DELETE FROM game_records ") //Odstránenie všetkých záznamov z databázy
    fun removeAllData()

}