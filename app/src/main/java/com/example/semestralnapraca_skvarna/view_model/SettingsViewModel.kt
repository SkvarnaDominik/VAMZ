package com.example.semestralnapraca_skvarna.view_model

import androidx.lifecycle.MutableLiveData
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