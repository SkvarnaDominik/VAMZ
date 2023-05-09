package com.example.semestralnapraca_skvarna.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameRecord::class], version = 1, exportSchema = false) //Nastavenie databázy
abstract class GameRecordDatabase : RoomDatabase() {

    abstract fun gameRecordDao(): GameRecordDao

    companion object { //"Singleton" - Zabezpečí aby v daný moment vždy existovala maximálne 1 inštancia databázy
    @Volatile
    private var INSTANCE: GameRecordDatabase? = null

    fun getDatabase(context: Context): GameRecordDatabase {
        val tempInstance = INSTANCE
        if (tempInstance != null) { //Ak už existuje inštancia databázy, vráti danú inštanciu
            return tempInstance
        }
        synchronized(this) { //Ak žiadna inštancia neexistuje, vytvorú ju
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