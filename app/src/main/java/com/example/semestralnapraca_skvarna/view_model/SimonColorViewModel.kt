package com.example.semestralnapraca_skvarna.view_model

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimonColorViewModel : ViewModel() {

    private var round = MutableLiveData<Int>(0)
    fun getRound(): LiveData<Int> {
        return round
    }

    private var sequence = ArrayList<Int>()
    fun getSequence(): ArrayList<Int> {
        return sequence
    }

    private var score = MutableLiveData<Int>()
    fun getIsScore(): LiveData<Int> {
        return score
    }

    private var isSame = MutableLiveData<Boolean>()
    fun getIsSame(): LiveData<Boolean> {
        return isSame
    }

    private var colorButton = MutableLiveData<Int>()
    fun getColorButton(): LiveData<Int> {
        return colorButton
    }

    fun pickRandomColorButton() { //vygenerovanie náhodného čísla z rozsahu 0-3
         addToSequence((0..3).random())
    }

    private fun addToSequence(pColorButton:Int) {
        sequence.add(pColorButton)
    }
}