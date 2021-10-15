package com.codingassignemnt.app.business.data.network

import com.codingassignemnt.app.business.domain.bean.Article


interface NetworkDataSource {

    suspend fun get(): List<Article>
}