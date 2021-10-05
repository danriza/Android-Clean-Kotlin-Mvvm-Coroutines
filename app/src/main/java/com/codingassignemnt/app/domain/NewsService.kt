package com.codingassignemnt.app.domain

import com.codingassignemnt.app.domain.bean.NewsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getAllNews(@Query("country") country: String,
                           @Query("sortBy") sortCriteria: String,
                           @Query("apiKey") API_KEY: String) : Response<NewsResponse>

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        var newsApiService: NewsService? = null

        fun getInstance() : NewsService {
            if (newsApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                newsApiService = retrofit.create(NewsService::class.java)
            }
            return newsApiService!!
        }

    }

}