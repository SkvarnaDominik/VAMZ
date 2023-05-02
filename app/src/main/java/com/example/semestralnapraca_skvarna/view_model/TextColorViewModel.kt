package com.example.semestralnapraca_skvarna.view_model

import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_skvarna.R

class TextColorViewModel: ViewModel() {

    private var colorButtons: IntArray = intArrayOf(R.drawable.btn_yellow, R.drawable.btn_blue, R.drawable.btn_red, R.drawable.btn_green)
    fun shuffleColorButtons() {
        colorButtons.shuffle()
    }
    fun getColorButtons(): IntArray {
        return colorButtons
    }

    private val textColors = arrayOf<String> ("Žltá", "Modrá", "Červená", "Zelená")
    fun getTextColors(): Array<String> {
        return textColors
    }
    fun shuffleTextColors() {
        textColors.shuffle()
        color = textColors[0]
    }

    private var color: String = ""
    fun getColor(): String {
        return color
    }

    val modes = arrayOf("Farba", "Text")
    fun shuffleModes() {
        modes.shuffle()
        mode = modes[0]
    }

    private var mode:String = ""
    fun getMode(): String {
        return mode
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

    private var chosenButton:Int = 0
    fun getChosenButton(): Int {
        return chosenButton
    }
    fun chooseColorButton() {
        chosenButton = pickRandomColorButton()
    }



    private fun pickRandomColorButton(): Int { //vygenerovanie náhodného čísla z rozsahu 0-3
        return (0..3).random()
    }
}