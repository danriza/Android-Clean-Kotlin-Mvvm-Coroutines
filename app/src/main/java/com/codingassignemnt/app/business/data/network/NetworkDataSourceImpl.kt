package com.codingassignemnt.app.business.data.network

import com.codingassignemnt.app.business.domain.bean.Article
import com.codingassignemnt.app.framework.datasource.network.ArticleRetrofitService
import com.codingassignemnt.app.framework.datasource.network.mappers.NetworkMapper

class NetworkDataSourceImpl
constructor(
    private val articleRetrofitService: ArticleRetrofitService,
    private val networkMapper: NetworkMapper
): NetworkDataSource {

    override suspend fun get(): List<Article> {
        val articleNetworkResponse = articleRetrofitService.get()
        return networkMapper.mapFromEntityList(articleNetworkResponse.articles)
    }

}