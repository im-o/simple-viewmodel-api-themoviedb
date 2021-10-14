package com.rivaldy.id.sampleviewmodel.data.repository

import com.rivaldy.id.sampleviewmodel.data.model.MovieResponse
import com.rivaldy.id.sampleviewmodel.data.network.ApiClient
import retrofit2.Call

/**
 * Created by rivaldy on 14/10/21
 * Find me on my Github -> https://github.com/im-o
 **/

class MovieRepository(
    private val apiClient: ApiClient
) {
    fun getMovies(): Call<MovieResponse> {
        return apiClient.apiTheMovieDb().getMovies()
    }
}