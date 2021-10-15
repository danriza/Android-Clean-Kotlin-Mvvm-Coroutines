package com.codingassignemnt.app.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticleNetworkResponse(

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("totalResults")
    @Expose
    val totalResults: Int,

    @SerializedName("articles")
    @Expose
    var articles: ArrayList<ArticleNetworkEntity> = ArrayList<ArticleNetworkEntity>()
)