package com.example.semestralnapraca_skvarna.view_model

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountDownViewModel: ViewModel() {

    private lateinit var timer: CountDownTimer

    private var isFinished = MutableLiveData<Boolean>()
    fun getIsFinished(): LiveData<Boolean> {
        return isFinished
    }

    private val seconds = MutableLiveData<Int>()
    fun getSeconds(): LiveData<Int> {
        return seconds
    }

    fun startTimer(pInitSeconds: Int) {
        timer = object : CountDownTimer((pInitSeconds * 1000).toLong(), 1000) {
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