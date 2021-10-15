package com.codingassignemnt.app.di

import com.codingassignemnt.app.business.data.cache.CacheDataSource
import com.codingassignemnt.app.business.data.network.NetworkDataSource
import com.codingassignemnt.app.business.interactor.GetArticles
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetArticles(
        cacheDataSource: CacheDataSource,
        networkDataSource: NetworkDataSource
    ): GetArticles {
        return GetArticles(cacheDataSource, networkDataSource)
    }
}














