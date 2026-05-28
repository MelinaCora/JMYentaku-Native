package com.jmyentaku.app.data.model

data class UserAnime(

    val animeId: Int = 0,

    val title: String = "",

    val imageUrl: String = "",

    val status: String = "planned",

    val updatedAt: Long = System.currentTimeMillis()
)