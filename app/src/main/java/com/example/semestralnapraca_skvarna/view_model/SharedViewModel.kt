package com.example.semestralnapraca_skvarna.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_skvarna.R

class SharedViewModel : ViewModel() {

    private var game = MutableLiveData<String>("SimonColor")
    fun getGame(): String {
        return game.value!!
    }
    fun getGameLiveData(): MutableLiveData<String> {
        return game
    }
    fun setGame(pGame:String) {
        game.value = pGame
    }

    private var difficulty = MutableLiveData<String>("Easy")
    fun getDifficulty(): String {
        return difficulty.value!!
    }
    fun getDifficultyLiveData(): MutableLiveData<String> {
        return difficulty
    }
    fun setDifficulty(pDifficulty:String) {
        difficulty.value = pDifficulty
    }

    private var isDifficultyChosen: Boolean = false
    fun getIsDifficultyChosen(): Boolean {
        return isDifficultyChosen
    }
    fun setIsDifficultyChosen(pIsDifficultyChosen:Boolean) {
        isDifficultyChosen = pIsDifficultyChosen
    }

    //private var score = MutableLiveData<Int>()
    private var score: Int = 0
    fun getScore(): Int {
        //return score.value!!
        return score
    }
    fun addScore() {
        //score.postValue(score.value!! + 1)
        score += 1
    }
    fun resetScore() {
        score = 0
    }

    private val profilePictureResources: IntArray = intArrayOf(
        R.drawable.michael,
        R.drawable.dwight,
        R.drawable.kevin,
        R.drawable.image
    )
    fun getProfilePictureResources(): IntArray{
        return profilePictureResources
    }

    private var indexOfProfilePicture = MutableLiveData<Int>(0)
    fun getIndexOfProfilePicture(): Int {
        return indexOfProfilePicture.value!!
    }
    fun setIndexOfProfilePicture(pIndex:Int) {
        indexOfProfilePicture.value = pIndex
    }

    private var username = MutableLiveData<String>("**********")
    fun getUsername(): String {
        return username.value!!
    }
    fun setUsername(pNewUsername:String) {
        username.value = pNewUsername
    }







    private var isDarkMode = MutableLiveData<Boolean>(true)
    fun getIsDarkMode(): Boolean {
        return isDarkMode.value!!
    }
    fun setIsDarkMode(pIsDarkMode:Boolean) {
        isDarkMode.value = pIsDarkMode
    }
}