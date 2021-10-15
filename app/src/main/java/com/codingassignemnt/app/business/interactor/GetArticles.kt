package com.codingassignemnt.app.business.interactor

import com.codingassignemnt.app.business.data.cache.CacheDataSource
import com.codingassignemnt.app.business.data.network.NetworkDataSource
import com.codingassignemnt.app.business.domain.bean.Article
import com.codingassignemnt.app.business.domain.state.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArticles constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
) {

    /**
     * Show loading
     * Get Articles from network
     * Insert Articles into cache
     * Show List<Article>
     */
    suspend fun execute(): Flow<DataState<List<Article>>> = flow {
        emit(DataState.Loading)
        val networkArticles = networkDataSource.get()
        if (networkArticles.isNotEmpty()) {
            cacheDataSource.deleteAll()
            cacheDataSource.insertList(networkArticles)
            val cachedArticles = cacheDataSource.get()
            emit(DataState.Success(cachedArticles))
        } else {
            emit(DataState.Error(GENERIC_ERROR))
        }
    }

    companion object {
        private const val GENERIC_ERROR = "Something went wrong"
    }

}