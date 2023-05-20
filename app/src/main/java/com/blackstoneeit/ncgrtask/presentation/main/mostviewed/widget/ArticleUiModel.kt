package com.blackstoneeit.ncgrtask.presentation.main.mostviewed.widget

import com.blackstoneeit.ncgrtask.data.model.response.Results

data class ArticleUiModel(
    val articleId: String,
    val articleUrl: String,
    val articleSource: String,
    val publishDate: String,
    val section: String,
    val articleAuthor: String,
    val articleTitle: String,
    val articleDescription: String,
    val articleImage: String?,
)

fun Results.toUiModel() = ArticleUiModel(
    articleId = id.orEmpty(),
    articleUrl = url.orEmpty(),
    articleSource = source.orEmpty(),
    publishDate = publishedDate.orEmpty(),
    section = section.orEmpty(),
    articleAuthor = byline.orEmpty(),
    articleTitle = title.orEmpty(),
    articleDescription = abstract.orEmpty(),
    articleImage = media.firstOrNull()?.mediaMetaData?.lastOrNull()?.url
)