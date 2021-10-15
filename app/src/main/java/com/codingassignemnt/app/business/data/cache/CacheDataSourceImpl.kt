package com.codingassignemnt.app.business.data.cache

import com.codingassignemnt.app.business.domain.bean.Article
import com.codingassignemnt.app.framework.datasource.cache.ArticleDaoService
import com.codingassignemnt.app.framework.datasource.cache.mappers.CacheMapper


class CacheDataSourceImpl
constructor(
    private val articleDaoService: ArticleDaoService,
    private val cacheMapper: CacheMapper
) : CacheDataSource {

    override suspend fun insert(article: Article): Long {
        return articleDaoService.insert(cacheMapper.mapToEntity(article))
    }

    override suspend fun insertList(articles: List<Article>) {
        for (article in articles) {
            articleDaoService.insert(cacheMapper.mapToEntity(article))
        }
    }

    override suspend fun get(): List<Article> {
        return cacheMapper.mapFromEntityList(articleDaoService.get())
    }

    override suspend fun deleteAll() {
        articleDaoService.delete()
    }

}
