package com.codingassignemnt.app.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingassignemnt.app.framework.datasource.cache.model.ArticleCacheEntity

@Database(entities = [ArticleCacheEntity::class ], version = 2)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object{
        val DATABASE_NAME: String = "article_db"
    }


}