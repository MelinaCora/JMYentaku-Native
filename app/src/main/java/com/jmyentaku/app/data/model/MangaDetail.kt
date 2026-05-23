package com.jmyentaku.app.data.model

data class MangaDetail(
    val mal_id: Int,
    val title: String,
    val title_japanese: String?,
    val synopsis: String?,
    val score: Double?,
    val favorites: Int,
    val chapters: Int?,
    val volumes: Int?,
    val status: String?,
    val genres: List<Genre>?,
    val images: MangaImages
)

data class MangaImages(val jpg: MangaImageUrl)
data class MangaImageUrl(val image_url: String)