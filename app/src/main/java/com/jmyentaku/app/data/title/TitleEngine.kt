package com.jmyentaku.app.data.title

object TitleEngine {

    fun calculateTitle(
        level: Int
    ): String {

        return when {

            level >= 20 -> "Anime Legend"

            level >= 15 -> "Anime Master"

            level >= 10 -> "Anime Veteran"

            level >= 5 -> "Anime Enthusiast"

            level >= 2 -> "Anime Beginner"

            else -> "Anime Explorer"
        }
    }

}