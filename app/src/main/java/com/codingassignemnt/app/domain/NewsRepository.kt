package com.codingassignemnt.app.domain

class NewsRepository constructor(private val retrofitService: NewsService) {

    suspend fun getAllNews() = retrofitService.getAllNews("us", "publishedAt", API_KEY)

    companion object {
        private const val API_KEY = "f71af7261c434b5d8be60816ed910d8b"
    }
}