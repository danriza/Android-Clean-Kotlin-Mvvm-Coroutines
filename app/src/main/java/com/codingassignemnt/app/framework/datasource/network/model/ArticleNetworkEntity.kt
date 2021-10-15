package com.codingassignemnt.app.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleNetworkEntity(

    @SerializedName("author")
    @Expose
    val author: String?,

    @SerializedName("title")
    @Expose
    val title: String?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("url")
    @Expose
    val url: String?,

    @SerializedName("urlToImage")
    @Expose
    val urlToImage: String?,

    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String?,

    @SerializedName("content")
    @Expose
    val content: String?,

    @SerializedName("source")
    @Expose
    val source: Source?,
)

data class Source (
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?
)




