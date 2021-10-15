package com.codingassignemnt.app.framework.datasource.cache

import com.codingassignemnt.app.framework.datasource.cache.database.ArticleDao
import com.codingassignemnt.app.framework.datasource.cache.model.ArticleCacheEntity

class ArticleDaoServiceImpl
constructor(
    private val articleDao: ArticleDao
) : ArticleDaoService {

    override suspend fun insert(articleEntity: ArticleCacheEntity): Long {
        return articleDao.insert(articleEntity)
    }

    override suspend fun get(): List<ArticleCacheEntity> {
        return articleDao.get()
    }

    override suspend fun delete(): Unit {
        articleDao.deleteAll()
    }
}