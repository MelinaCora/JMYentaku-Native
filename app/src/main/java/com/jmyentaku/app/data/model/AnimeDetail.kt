package com.jmyentaku.app.data.model

data class AnimeDetail(
    val mal_id: Int,
    val title: String,
    val title_japanese: String?,
    val synopsis: String?,
    val score: Double?,
    val favorites: Int,
    val episodes: Int?,
    val status: String?,
    val genres: List<Genre>?,
    val images: AnimeImages
)

data class Genre(val name: String)