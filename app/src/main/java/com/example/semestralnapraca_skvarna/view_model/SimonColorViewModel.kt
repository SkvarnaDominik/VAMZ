package com.example.semestralnapraca_skvarna.view_model

import androidx.lifecycle.ViewModel

class SimonColorViewModel : ViewModel() { //Trieda, ktorá pracuje s dátami a sprístupňuje ich danému/daným fragmentom

    private var round: Int = 1
    fun getRound(): Int {
        return round
    }
    fun addRound() {
        round += 1
    }

    private var colorButtonsPressed: Int = 0
    fun getColorButtonsPressed(): Int {
        return colorButtonsPressed
    }
    fun addColorButtonsPressed() {
        colorButtonsPressed += 1
    }
    fun setColorButtonsPressed(pNumberPressed:Int) {
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

    private var isSame:Boolean = true
    fun getIsSame(): Boolean {
        return isSame
    }
    fun setIsSame(pIsSame:Boolean) {
        isSame = pIsSame
    }

    fun pickRandomColorButton(pMode:String) { //vygenerovanie náhodného čísla z rozsahu 0-3
        if (pMode == "Easy")
            addToGameSequence((0..2).random())
        else
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