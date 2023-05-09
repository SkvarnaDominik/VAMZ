package com.example.semestralnapraca_skvarna.view_model

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_skvarna.R

class TextColorViewModel: ViewModel() {

    private var colorButtons: IntArray = intArrayOf(
        R.drawable.btn_yellow,
        R.drawable.btn_blue,
        R.drawable.btn_red,
        R.drawable.btn_green
    )

    fun shuffleColorButtons() {
       colorButtons.shuffle()
    }

    fun getColorButtons(): IntArray {
        return colorButtons
    }

    private val textColors = arrayOf("Yellow", "Blue", "Red", "Green")
    fun getTextColors(): Array<String> {
        return textColors
    }

    fun shuffleTextColors() {
        textColors.shuffle()
    }
    fun setColor(pColor:String) {
        color = pColor
    }

    private var color: String = ""
    fun getTextColor(): String {
        return color
    }

    private val modes = arrayOf("Text", "Color")
    fun shuffleModes() {
        modes.shuffle()
    }
    fun getModes(): Array<String> {
        return modes
    }
    fun setMode(pMode:String) {
        mode = pMode
    }

    private var mode: String = ""
    fun getMode(): String {
        return mode
    }

    //private var isSame = MutableLiveData<Boolean>(true)
    private var isSame: Boolean = true
    fun getIsSame(): Boolean {
        //  return isSame.value!!
        return isSame
    }

    fun setIsSame(pIsSame: Boolean) {
        //isSame.postValue(pIsSame)
        isSame = pIsSame
    }

    private var winningColorButton: Int = 0
    fun getWinningColorButton(): Int {
        return winningColorButton
    }
    fun setWinningColorButton(pWinningColorButton:Int) {
        winningColorButton = pWinningColorButton
    }

    private lateinit var timer: CountDownTimer

    private var isFinished = MutableLiveData<Boolean>()
    fun getIsFinished(): LiveData<Boolean> {
        return isFinished
    }

    private val seconds = MutableLiveData<Int>()
    fun getSeconds(): LiveData<Int> {
        return seconds
    }

    private var isTicking: Boolean = false

    fun startTimer(pInitSeconds: Int) {
        if (isTicking)
            return
        timer = object : CountDownTimer((pInitSeconds * 1000).toLong(), 1000) {
            override fun onTick(p0: Long) {
                val timeLeft = p0/1000
                seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                isFinished.value = true
            }
        }.start()
        isTicking = true
    }

    fun restartTimer() {
        timer.cancel()
        timer.start()
    }
}