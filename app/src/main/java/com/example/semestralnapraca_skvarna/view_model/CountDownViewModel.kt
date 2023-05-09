package com.example.semestralnapraca_skvarna.view_model

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountDownViewModel: ViewModel() { //Trieda, ktorá pracuje s dátami a sprístupňuje ich danému/daným fragmentom

    private lateinit var timer: CountDownTimer

    private var isFinished = MutableLiveData<Boolean>()
    fun getIsFinished(): LiveData<Boolean> {
        return isFinished
    }

    private val seconds = MutableLiveData<Int>()
    fun getSeconds(): LiveData<Int> {
        return seconds
    }

    private var isTicking: Boolean = false //Flag, ktorý vyjadruje, či už timer raz beží

    fun startTimer(pInitSeconds: Int) {
        if (isTicking)
            return //Ak už timer raz beži, druhýkrát sa nezapne
        timer = object : CountDownTimer((pInitSeconds * 1000).toLong(), 1000) { //Nastavenie dĺžky timer-u a ako frekvencie opakovania danej akcie
            override fun onTick(p0: Long) { //Metóda čo sa má stať keď timer "tikne"
                val timeLeft = p0/1000
                seconds.value = timeLeft.toInt()
            }

            override fun onFinish() { //Metóda čo sa má stať keď timer skončí
                isFinished.value = true
            }
        }.start()
        isTicking = true //Flag, ktorý vyjadruje, že timer beží
    }
}