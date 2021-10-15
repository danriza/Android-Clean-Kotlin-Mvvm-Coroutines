package com.codingassignemnt.app.framework.datasource.cache

import com.codingassignemnt.app.framework.datasource.cache.model.ArticleCacheEntity

interface ArticleDaoService {

    suspend fun insert(articleEntity: ArticleCacheEntity): Long

    suspend fun get(): List<ArticleCacheEntity>

    suspend fun delete(): Unit

}