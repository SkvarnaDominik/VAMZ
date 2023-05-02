package com.example.semestralnapraca_skvarna.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimonColorViewModel : ViewModel() {

    //private var round = MutableLiveData<Int>(1)
    private var round: Int = 1
    fun getRound(): Int {
        //return round.value!!
        return round
    }
    fun addRound() {
        //round.postValue(round.value!! + 1)
        round += 1
    }

    //private var colorButtonsPressed = MutableLiveData<Int>(0)
    private var colorButtonsPressed: Int = 0
    fun getColorButtonsPressed(): Int {
        //return colorButtonsPressed.value!!
        return colorButtonsPressed
    }
    fun addColorButtonsPressed() {
        //colorButtonsPressed.postValue(colorButtonsPressed.value!! + 1)
        colorButtonsPressed += 1
    }
    fun setColorButtonsPressed(pNumberPressed:Int) {
        //colorButtonsPressed.postValue(pNumberPressed)
        colorButtonsPressed = pNumberPressed
    }

    private var gameSequence = ArrayList<Int>()
    fun getGameSequence(): ArrayList<Int> {
        return gameSequence
    }

    private var userSequence = ArrayList<Int>()
    fun getUserSequence(): ArrayList<Int> {
        return userSequence
    }

    //private var isSame = MutableLiveData<Boolean>(true)
    private var isSame:Boolean = true
    fun getIsSame(): Boolean {
      //  return isSame.value!!
        return isSame
    }
    fun setIsSame(pIsSame:Boolean) {
        //isSame.postValue(pIsSame)
        isSame = pIsSame
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
    fun clearUserSequence() {
        userSequence.clear()
    }
}