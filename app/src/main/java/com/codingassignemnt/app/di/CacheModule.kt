package com.codingassignemnt.app.di

import android.content.Context
import androidx.room.Room
import com.codingassignemnt.app.business.data.cache.CacheDataSource
import com.codingassignemnt.app.business.data.cache.CacheDataSourceImpl
import com.codingassignemnt.app.business.domain.bean.Article
import com.codingassignemnt.app.business.domain.util.EntityMapper
import com.codingassignemnt.app.framework.datasource.cache.ArticleDaoService
import com.codingassignemnt.app.framework.datasource.cache.ArticleDaoServiceImpl
import com.codingassignemnt.app.framework.datasource.cache.database.ArticleDao
import com.codingassignemnt.app.framework.datasource.cache.mappers.CacheMapper
import com.codingassignemnt.app.framework.datasource.cache.database.ArticleDatabase
import com.codingassignemnt.app.framework.datasource.cache.model.ArticleCacheEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): EntityMapper<ArticleCacheEntity, Article> {
        return CacheMapper()
    }

    @Singleton
    @Provides
    fun provideArticleDb(@ApplicationContext context: Context): ArticleDatabase {
        return Room
            .databaseBuilder(
                context,
                ArticleDatabase::class.java,
                ArticleDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArticleDAO(articleDatabase: ArticleDatabase): ArticleDao {
        return articleDatabase.articleDao()
    }

    @Singleton
    @Provides
    fun provideArticleDaoService(
        articleDao: ArticleDao
    ):ArticleDaoService{
        return ArticleDaoServiceImpl(articleDao)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(
        articleDaoService: ArticleDaoService,
        cacheMapper: CacheMapper
    ): CacheDataSource {
        return CacheDataSourceImpl(articleDaoService, cacheMapper)
    }


}

























