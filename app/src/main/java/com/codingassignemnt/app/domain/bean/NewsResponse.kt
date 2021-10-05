package com.codingassignemnt.app.domain.bean

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    var articles: ArrayList<NewsBean> = ArrayList<NewsBean>()
)