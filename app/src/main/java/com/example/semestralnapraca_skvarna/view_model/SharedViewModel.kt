package com.example.semestralnapraca_skvarna.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_skvarna.R

class SharedViewModel : ViewModel() { //Trieda, ktorá pracuje s dátami a sprístupňuje ich danému/daným fragmentom

    private var isFirstRound: Boolean =true
    fun getIsFirstRound(): Boolean {
        return isFirstRound
    }
    fun setIsFirstRound(pIsFirstRound:Boolean) {
        isFirstRound = pIsFirstRound
    }

    private var game = MutableLiveData("SimonColor")
    fun getGame(): String {
        return game.value!!
    }
    fun getGameLiveData(): MutableLiveData<String> {
        return game
    }
    fun setGame(pGame:String) {
        game.value = pGame
    }

    private var difficulty = MutableLiveData("Easy")
    fun getDifficulty(): String {
        return difficulty.value!!
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

    private var score: Int = 0
    fun getScore(): Int {
        return score
    }
    fun addScore() {
        score += 1
    }
    fun resetScore() {
        score = 0
    }

    private var scoreType: String = ""
    fun getScoreType(): String {
        return scoreType
    }
    fun setScoreType(pScoreType: String) {
        scoreType = pScoreType
    }

    private val profilePictureResources: IntArray = intArrayOf(
        R.drawable.profile_1,
        R.drawable.profile_2,
        R.drawable.profile_3,
        R.drawable.profile_4
    )
    fun getProfilePictureResources(): IntArray{
        return profilePictureResources
    }

    private var indexOfProfilePicture = MutableLiveData(0)
    fun getIndexOfProfilePicture(): Int {
        return indexOfProfilePicture.value!!
    }
    fun setIndexOfProfilePicture(pIndex:Int) {
        indexOfProfilePicture.value = pIndex
    }

    private var username = MutableLiveData("**********")
    fun getUsername(): String {
        return username.value!!
    }
    fun setUsername(pNewUsername:String) {
        username.value = pNewUsername
    }
}