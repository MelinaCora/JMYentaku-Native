package com.jmyentaku.app.data.network

import com.jmyentaku.app.data.model.AnimeResponse
import retrofit2.http.GET

interface AnimeApiService {

    @GET("top/anime")

    suspend fun getTopAnime(): AnimeResponse
}