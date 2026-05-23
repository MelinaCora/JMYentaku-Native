package com.jmyentaku.app.data.repository

import com.jmyentaku.app.data.model.Anime
import com.jmyentaku.app.data.network.RetrofitInstance

class MangaRepository {

    suspend fun getTopManga(): List<Anime> {

        return RetrofitInstance.api
            .getTopManga()
            .data
    }
}