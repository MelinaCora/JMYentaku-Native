package com.jmyentaku.app.data.repository

import com.jmyentaku.app.data.model.Anime
import com.jmyentaku.app.data.network.RetrofitInstance

class AnimeRepository {

    suspend fun getTopAnime(): List<Anime> {

        return RetrofitInstance
            .api
            .getTopAnime()
            .data
    }
}