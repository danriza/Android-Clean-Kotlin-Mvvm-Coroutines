package com.codingassignemnt.app.domain.util

import com.codingassignemnt.app.domain.bean.NewsBean
import com.codingassignemnt.app.ui.NewsUiModel

class UiMapper {

    fun mapToUiNewsModel(newsBean: NewsBean): NewsUiModel {
        return NewsUiModel(
            newsBean.title,
            newsBean.description,
            newsBean.urlToImage,
            newsBean.publishedAt,
            newsBean.author
        )
    }
}