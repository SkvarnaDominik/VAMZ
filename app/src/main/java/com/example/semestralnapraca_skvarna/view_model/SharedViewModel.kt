package com.example.semestralnapraca_skvarna.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_skvarna.R

class SharedViewModel : ViewModel() {

    private var game = MutableLiveData<String>()
    fun getGame(): LiveData<String> {
        return game
    }
    fun setGame(pGame:String) {
        game.value = pGame
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

    private var username = MutableLiveData<String>("Template")
    fun getNewUsername(): String {
        return username.value!!
    }
    fun setNewUsername(pNewUsername:String) {
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