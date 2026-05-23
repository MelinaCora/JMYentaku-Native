package com.jmyentaku.app.data.network

import com.jmyentaku.app.data.model.AnimeDetail
import com.jmyentaku.app.data.model.MangaDetail
import com.jmyentaku.app.data.model.VoiceActorDetail
import com.jmyentaku.app.data.remote.AnimeDetailResponse
import com.jmyentaku.app.data.remote.AnimeResponse
import com.jmyentaku.app.data.remote.MangaDetailResponse
import com.jmyentaku.app.data.remote.MangaResponse
import com.jmyentaku.app.data.remote.VoiceActorDetailResponse
import com.jmyentaku.app.data.remote.VoiceActorResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApiService {

    //home
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse

    @GET("top/manga")
    suspend fun getTopManga(): MangaResponse

    @GET("top/people")
    suspend fun getTopVoiceActors(): VoiceActorResponse

    // Detalles
    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): AnimeDetailResponse

    @GET("manga/{id}")
    suspend fun getMangaDetails(@Path("id") id: Int): MangaDetailResponse

    @GET("people/{id}")
    suspend fun getVoiceActorDetails(@Path("id") id: Int): VoiceActorDetailResponse
}