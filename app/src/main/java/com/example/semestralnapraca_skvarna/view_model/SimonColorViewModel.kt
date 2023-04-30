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

    fun add() {
        sequence.add(0)
        sequence.add(1)
        sequence.add(1)
        sequence.add(3)
        sequence.add(0)
        sequence.add(2)
        sequence.add(1)

    }
}