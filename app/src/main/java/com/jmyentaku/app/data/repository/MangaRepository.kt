package com.jmyentaku.app.data.repository

import com.jmyentaku.app.data.model.Anime
import com.jmyentaku.app.data.network.RetrofitInstance

class MangaRepository {

    suspend fun getTopManga(): List<Anime> {

        return RetrofitInstance.api
            .getTopManga()
            .data
    }

    suspend fun searchManga(
        query: String
    ): List<Anime> {

        return RetrofitInstance.api
            .searchManga(query)
            .data
    }
}