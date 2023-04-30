package com.example.semestralnapraca_skvarna.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var game = MutableLiveData<String>()
    fun getGame(): LiveData<String> {
        return game
    }
    fun setGame(pGame:String) {
        game.value = pGame
    }
}