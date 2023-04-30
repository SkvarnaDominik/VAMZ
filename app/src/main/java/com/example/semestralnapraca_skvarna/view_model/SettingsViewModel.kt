package com.example.semestralnapraca_skvarna.view_model

import androidx.lifecycle.ViewModel
import com.example.semestralnapraca_skvarna.R

class SettingsViewModel: ViewModel() {

    private val profilePictureResources: IntArray = intArrayOf(
        R.drawable.michael,
        R.drawable.dwight,
        R.drawable.kevin,
        R.drawable.image
    )
    fun getProfilePictureResources(): IntArray{
        return profilePictureResources
    }

    private var indexOfProfilePicture:Int = 0
    fun getIndexOfProfilePicture(): Int{
        return indexOfProfilePicture
    }
    fun setIndexOfProfilePicture(pIndex:Int) {
        indexOfProfilePicture= pIndex
    }

    private var username:String = "Template"
    fun getNewUsername(): String{
        return username
    }
    fun setNewUsername(pNewUsername:String) {
        username = pNewUsername
    }

    private var isDarkMode:Boolean = true
    fun getIsDarkMode(): Boolean{
        return isDarkMode
    }
    fun setIsDarkMode(pIsDarkMode:Boolean) {
        isDarkMode = pIsDarkMode
    }
}