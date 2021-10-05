package com.codingassignemnt.app.domain.bean

import com.google.gson.annotations.SerializedName

data class NewsBean (

    @SerializedName("author")
    val author: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("urlToImage")
    val urlToImage: String,

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("source")
    val source: Source,
)

data class Source (
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String
)


