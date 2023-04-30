package com.example.semestralnapraca_skvarna.view_model

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimonColorViewModel : ViewModel() {

    private var round = MutableLiveData<Int>(0)
    private var colorOrder = ArrayList<Int>()
    private var score = MutableLiveData<Int>()
    private var same = MutableLiveData<Boolean>()
    private var colorButton = MutableLiveData<Int>()

    private lateinit var timer: CountDownTimer

    private var isFinished = MutableLiveData<Boolean>()
    fun getIsFinished(): LiveData<Boolean> {
        return isFinished
    }

    private val seconds = MutableLiveData<Int>()
    fun getSeconds(): LiveData<Int> {
        return seconds
    }

    fun startTimer() {
        timer = object : CountDownTimer((round.value!! * 1000).toLong(), 1000) {
            override fun onTick(p0: Long) {
                val timeLeft = p0/1000
                seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                isFinished.value = true
            }
        }.start()
    }
}