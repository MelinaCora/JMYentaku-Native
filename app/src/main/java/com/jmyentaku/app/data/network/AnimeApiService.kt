package com.jmyentaku.app.data.network

import com.jmyentaku.app.data.model.AnimeResponse
import retrofit2.http.GET
import com.jmyentaku.app.data.model.MangaResponse

interface AnimeApiService {

    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse

    @GET("top/manga")
    suspend fun getTopManga(): MangaResponse
}