package com.jmyentaku.app.data.repository

import com.jmyentaku.app.data.model.AnimeDetail
import com.jmyentaku.app.data.model.MangaDetail
import com.jmyentaku.app.data.model.VoiceActorDetail
import com.jmyentaku.app.data.network.RetrofitInstance

class DetailRepository {

    suspend fun getAnimeDetails(animeId: Int): AnimeDetail {
        return RetrofitInstance.api
            .getAnimeDetails(animeId)
            .data
    }

    suspend fun getMangaDetails(mangaId: Int): MangaDetail {
        return RetrofitInstance.api
            .getMangaDetails(mangaId)
            .data
    }

    suspend fun getVoiceActorDetails(actorId: Int): VoiceActorDetail {
        return RetrofitInstance.api
            .getVoiceActorDetails(actorId)
            .data
    }
}