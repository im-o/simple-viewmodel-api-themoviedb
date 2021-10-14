package com.rivaldy.id.sampleviewmodel.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rivaldy.id.sampleviewmodel.data.repository.MovieRepository

/**
 * Created by rivaldy on 14/10/21
 * Find me on my Github -> https://github.com/im-o
 **/

class MainViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(MovieRepository::class.java)
            return constructor.newInstance(repository)
        } catch (err: Exception) {
            Log.e("ERROR", err.toString())
        }
        return super.create(modelClass)
    }
}