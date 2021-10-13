package com.rivaldy.id.sampleviewmodel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

/**
 * Created by rivaldy on 13/10/21
 * Find me on my Github -> https://github.com/im-o
 **/

class MainViewModel : ViewModel() {
    fun loadUserData() = liveData {
        emit(sampleData())
    }

    private fun sampleData(): String {
        return "Hello ini sample data"
    }
}