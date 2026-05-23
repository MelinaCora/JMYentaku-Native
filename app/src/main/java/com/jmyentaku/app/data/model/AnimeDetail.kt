package com.jmyentaku.app.data.model

data class AnimeDetail(
    val mal_id: Int,
    val title: String,
    val title_japanese: String?,
    val title_english: String?,
    val synopsis: String?,
    val background: String?,
    val score: Double?,
    val scored_by: Int?,
    val rank: Int?,
    val popularity: Int?,
    val members: Int?,
    val favorites: Int,
    val episodes: Int?,
    val duration: String?,
    val status: String?,
    val rating: String?,
    val source: String?,
    val season: String?,
    val year: Int?,
    val aired: Aired?,
    val studios: List<Studio>?,
    val genres: List<Genre>?,
    val themes: List<Theme>?,
    val demographics: List<Demographic>?,
    val trailer: Trailer?,
    val images: AnimeImages
)

data class Aired(
    val from: String?,
    val to: String?,
    val string: String?
)

data class Studio(
    val name: String
)

data class Genre(
    val name: String
)

data class Theme(
    val name: String
)

data class Demographic(
    val name: String
)

data class Trailer(
    val youtube_id: String?,
    val url: String?,
    val embed_url: String?
)
