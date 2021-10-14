package com.rivaldy.id.sampleviewmodel.data.network

import com.google.gson.GsonBuilder
import com.rivaldy.id.sampleviewmodel.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by rivaldy on 14/10/21
 * Find me on my Github -> https://github.com/im-o
 **/

class ApiClient {
    companion object {
        const val PATH_API_KEY = "api_key"
        const val PATH_DEFAULT_LANG = "language"
        const val DEFAULT_LANG = "en-US"
    }
    private fun doRequest(): Retrofit {
        val gson = GsonBuilder().create()
        val loggingInterceptor =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter(PATH_API_KEY, BuildConfig.API_KEY)
                    .addQueryParameter(PATH_DEFAULT_LANG, DEFAULT_LANG)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun apiTheMovieDb(): ApiTMDB {
        return doRequest().create(ApiTMDB::class.java)
    }
}