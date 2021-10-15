package com.codingassignemnt.app.di

import com.codingassignemnt.app.business.data.network.NetworkDataSource
import com.codingassignemnt.app.business.data.network.NetworkDataSourceImpl
import com.codingassignemnt.app.business.domain.bean.Article
import com.codingassignemnt.app.business.domain.util.EntityMapper
import com.codingassignemnt.app.framework.datasource.network.ArticleRetrofitService
import com.codingassignemnt.app.framework.datasource.network.ArticleRetrofitServiceImpl
import com.codingassignemnt.app.framework.datasource.network.mappers.NetworkMapper
import com.codingassignemnt.app.framework.datasource.network.model.ArticleNetworkEntity
import com.codingassignemnt.app.framework.datasource.network.retrofit.ArticleRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkMapper(): EntityMapper<ArticleNetworkEntity, Article> {
        return NetworkMapper()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson:  Gson): Retrofit.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideArticleService(retrofit: Retrofit.Builder): ArticleRetrofit {
        return retrofit
            .build()
            .create(ArticleRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(
        articleRetrofit: ArticleRetrofit
    ): ArticleRetrofitService {
        return ArticleRetrofitServiceImpl(articleRetrofit)
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        articleRetrofitService: ArticleRetrofitService,
        networkMapper: NetworkMapper
    ): NetworkDataSource {
        return NetworkDataSourceImpl(articleRetrofitService, networkMapper)
    }

}




















