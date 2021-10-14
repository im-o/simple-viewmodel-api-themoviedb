package com.rivaldy.id.sampleviewmodel.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldy.id.sampleviewmodel.data.model.MovieResponse
import com.rivaldy.id.sampleviewmodel.data.model.MovieResult
import com.rivaldy.id.sampleviewmodel.data.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by rivaldy on 13/10/21
 * Find me on my Github -> https://github.com/im-o
 **/

class MainViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    private val _getMovies = MutableLiveData<MutableList<MovieResult>>()
    val loading: LiveData<Boolean> = _loading
    val getMovies: LiveData<MutableList<MovieResult>> = _getMovies


    fun getMovies() = viewModelScope.launch {
        _loading.value = true
        repository.getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _loading.value = false
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _loading.value = false
                if (response.isSuccessful) {
                    val movieList = response.body()?.results ?: return
                    _getMovies.value = movieList
                }
            }
        })
    }
}