package com.codingassignemnt.app.business.data.cache

import com.codingassignemnt.app.business.domain.bean.Article


interface CacheDataSource {

    suspend fun insert(article: Article): Long

    suspend fun insertList(articleList: List<Article>)

    suspend fun get(): List<Article>

    suspend fun deleteAll(): Unit
}