package com.codingassignemnt.app.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingassignemnt.app.framework.datasource.cache.model.ArticleCacheEntity

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleEntity: ArticleCacheEntity): Long

    @Query("SELECT * FROM articles")
    suspend fun get(): List<ArticleCacheEntity>

    @Query("DELETE FROM articles")
    suspend fun deleteAll()
}