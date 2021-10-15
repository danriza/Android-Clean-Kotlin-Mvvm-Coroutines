package com.codingassignemnt.app.framework.datasource.network

import com.codingassignemnt.app.framework.datasource.network.model.ArticleNetworkResponse

interface ArticleRetrofitService {

    suspend fun get(): ArticleNetworkResponse
}