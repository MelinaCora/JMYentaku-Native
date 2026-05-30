package com.jmyentaku.app.data.model

data class UserAnime(
    val animeId: Int = 0,
    val title: String = "",
    val imageUrl: String = "",
    val type: String = "anime",  //  "anime" o "manga"
    val progress: Int = 0,
    val total: Int = 0,
    val status: String = "planned",
    val updatedAt: Long = System.currentTimeMillis()
)