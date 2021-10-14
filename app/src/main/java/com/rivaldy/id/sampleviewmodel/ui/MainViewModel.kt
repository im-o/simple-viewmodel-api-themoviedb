package com.rivaldy.id.sampleviewmodel.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by rivaldy on 13/10/21
 * Find me on my Github -> https://github.com/im-o
 **/

class MainViewModel : ViewModel() {
    private val _userData: MutableLiveData<String> = MutableLiveData()
    val userData: LiveData<String> = _userData

    fun loadUserData() = viewModelScope.launch {
        _userData.value = "Hello, Click button below to change me!"
    }

    fun setSampleData() {
        _userData.value = "Yay! you change me with live data : ${Calendar.getInstance().time.time}"
    }
}