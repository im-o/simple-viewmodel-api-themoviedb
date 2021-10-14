package com.rivaldy.id.sampleviewmodel.data.network

import com.rivaldy.id.sampleviewmodel.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by rivaldy on 14/10/21
 * Find me on my Github -> https://github.com/im-o
 **/

interface ApiTMDB {
    @GET("discover/movie") //discover/movie?api_key={your api key}&language={language}
    fun getMovies(): Call<MovieResponse>
}