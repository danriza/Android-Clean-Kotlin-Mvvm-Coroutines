package com.codingassignemnt.app.framework.datasource.network.retrofit

import com.codingassignemnt.app.framework.datasource.network.model.ArticleNetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleRetrofit {

    @GET("top-headlines")
    suspend fun getAllNews(@Query("country") country: String,
                           @Query("sortBy") sortCriteria: String,
                           @Query("apiKey") API_KEY: String) : Response<ArticleNetworkResponse>
}