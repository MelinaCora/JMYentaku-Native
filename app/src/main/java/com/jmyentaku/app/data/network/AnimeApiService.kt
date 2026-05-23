package com.jmyentaku.app.data.network

import com.jmyentaku.app.data.remote.AnimeResponse
import retrofit2.http.GET
import com.jmyentaku.app.data.remote.MangaResponse
import com.jmyentaku.app.data.remote.VoiceActorResponse

interface AnimeApiService {

    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse

    @GET("top/manga")
    suspend fun getTopManga(): MangaResponse

    @GET("top/people")
    suspend fun getTopVoiceActors(): VoiceActorResponse
}