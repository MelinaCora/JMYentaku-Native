package com.jmyentaku.app.data.model

fun AnimeDetail.toAnime(): Anime {

    return Anime(

        mal_id = mal_id,

        title = title,

        synopsis = synopsis,

        score = score,

        images = images,

        favorites = favorites
    )
}

fun MangaDetail.toAnime(): Anime {

    return Anime(

        mal_id = mal_id,

        title = title,

        synopsis = synopsis,

        score = score,

        images = AnimeImages(

            jpg = AnimeImageUrl(

                image_url = images.jpg.image_url
            )
        ),

        favorites = favorites
    )
}