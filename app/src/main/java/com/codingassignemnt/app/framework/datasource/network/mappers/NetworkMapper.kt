package com.codingassignemnt.app.framework.datasource.network.mappers

import com.codingassignemnt.app.business.domain.bean.Article
import com.codingassignemnt.app.business.domain.util.EntityMapper
import com.codingassignemnt.app.framework.datasource.network.model.ArticleNetworkEntity
import com.codingassignemnt.app.framework.datasource.network.model.Source
import javax.inject.Inject

class NetworkMapper
@Inject
constructor(): EntityMapper<ArticleNetworkEntity, Article> {

    override fun mapFromEntity(entity: ArticleNetworkEntity): Article {
        return Article(
            id = entity.source?.id,
            title = entity.title,
            description = entity.description,
            imageUrl = entity.urlToImage,
            articleDate = entity.publishedAt,
            author = entity.author
        )
    }

    override fun mapToEntity(domainModel: Article): ArticleNetworkEntity {
        return ArticleNetworkEntity(
            source = Source(domainModel.id, ""),
            title = domainModel.title,
            description = domainModel.description,
            urlToImage = domainModel.imageUrl,
            publishedAt = domainModel.articleDate,
            author = domainModel.author,
            url = "",
            content = ""
        )
    }


    fun mapFromEntityList(entities: List<ArticleNetworkEntity>): List<Article>{
        return entities.map { mapFromEntity(it) }
    }

}





















