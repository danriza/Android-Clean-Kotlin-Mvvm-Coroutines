package com.codingassignemnt.app.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleCacheEntity(

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String?,

    @ColumnInfo(name = "articleDate")
    var articleDate: String?,

    @ColumnInfo(name = "author")
    var author: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}



