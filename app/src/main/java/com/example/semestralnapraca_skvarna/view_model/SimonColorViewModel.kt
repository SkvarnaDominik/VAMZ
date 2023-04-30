package com.example.semestralnapraca_skvarna.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimonColorViewModel : ViewModel() {

    private var round = MutableLiveData<Int>(1)
    fun getRound(): Int {
        return round.value!!
    }
    fun addRound() {
        round.value = round.value!! + 1
    }

    private var colorButtonsPressed = MutableLiveData<Int>(0)
    fun getColorButtonsPressed(): Int {
        return colorButtonsPressed.value!!
    }
    fun addColorButtonsPressed() {
        colorButtonsPressed.value = colorButtonsPressed.value!! + 1
    }

    private var gameSequence = ArrayList<Int>()
    fun getGameSequence(): ArrayList<Int> {
        return gameSequence
    }

    private var userSequence = ArrayList<Int>()
    fun getUserSequence(): ArrayList<Int> {
        return userSequence
    }

    private var score = MutableLiveData<Int>()
    fun getIsScore(): LiveData<Int> {
        return score
    }

    private var isSame = MutableLiveData<Boolean>(true)
    fun getIsSame(): Boolean {
        return isSame.value!!
    }
    fun setIsSame(pIsSame:Boolean) {
        isSame.value = pIsSame
    }

    private var colorButton = MutableLiveData<Int>()
    fun getColorButton(): LiveData<Int> {
        return colorButton
    }

    fun pickRandomColorButton() { //vygenerovanie náhodného čísla z rozsahu 0-3
         addToGameSequence((0..3).random())
    }

    private fun addToGameSequence(pColorButton:Int) {
        gameSequence.add(pColorButton)
    }

    fun addToUserSequence(pColorButton:Int) {
        userSequence.add(pColorButton)
    }
}