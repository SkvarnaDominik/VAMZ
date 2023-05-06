package com.example.semestralnapraca_skvarna.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameRecord::class], version = 1, exportSchema = false)
abstract class GameRecordDatabase : RoomDatabase() {

    abstract fun gameRecordDao(): GameRecordDao

    companion object {
    @Volatile
    private var INSTANCE: GameRecordDatabase? = null

    fun getDatabase(context: Context): GameRecordDatabase {
        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return tempInstance
        }
        synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                GameRecordDatabase::class.java,
                "game_records"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
    }
}