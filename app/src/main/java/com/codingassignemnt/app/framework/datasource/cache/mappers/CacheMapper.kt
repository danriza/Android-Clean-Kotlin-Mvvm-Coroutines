package com.codingassignemnt.app.framework.datasource.cache.mappers

import com.codingassignemnt.app.business.domain.bean.Article
import com.codingassignemnt.app.business.domain.util.EntityMapper
import com.codingassignemnt.app.framework.datasource.cache.model.ArticleCacheEntity
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): EntityMapper<ArticleCacheEntity, Article> {

    override fun mapFromEntity(entity: ArticleCacheEntity): Article {
        return Article(
            id = entity.id.toString(),
            title = entity.title,
            description = entity.description,
            imageUrl = entity.imageUrl,
            author = entity.author,
            articleDate = entity.articleDate
        )
    }

    override fun mapToEntity(domainModel: Article): ArticleCacheEntity {
        return ArticleCacheEntity(
            title = domainModel.title,
            description = domainModel.description,
            imageUrl = domainModel.imageUrl,
            author = domainModel.author,
            articleDate = domainModel.articleDate
        )
    }

    fun mapFromEntityList(entities: List<ArticleCacheEntity>): List<Article>{
        return entities.map { mapFromEntity(it) }
    }
}











