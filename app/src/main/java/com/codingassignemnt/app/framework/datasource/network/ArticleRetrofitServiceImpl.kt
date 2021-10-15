package com.codingassignemnt.app.framework.datasource.network

import com.codingassignemnt.app.framework.datasource.network.model.ArticleNetworkResponse
import com.codingassignemnt.app.framework.datasource.network.retrofit.ArticleRetrofit

class ArticleRetrofitServiceImpl
constructor(
    private val articleRetrofit: ArticleRetrofit
): ArticleRetrofitService {

    override suspend fun get(): ArticleNetworkResponse {
        val resp = articleRetrofit.getAllNews(COUNTRY, SORT_CRITERIA, API_KEY )
        if (resp.isSuccessful){
            resp.body()?.let {
                return it
            }
        }
        throw Exception("Something went wrong !")
    }

    companion object {
        private const val API_KEY = "f71af7261c434b5d8be60816ed910d8b"
        private const val COUNTRY = "us"
        private const val SORT_CRITERIA = "publishedAt"
    }
}