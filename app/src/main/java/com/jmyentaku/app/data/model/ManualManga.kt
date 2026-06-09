package com.jmyentaku.app.data.model

data class ManualManga(

    val id: String = "",

    val title: String = "",

    val author: String = "",

    val chapters: Int = 0,

    val genre: String = "",

    val description: String = "",

    //tracker
    val currentChapter: Int = 0,

    val status: String = "reading"
)