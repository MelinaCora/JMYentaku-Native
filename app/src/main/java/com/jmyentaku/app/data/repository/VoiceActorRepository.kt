package com.jmyentaku.app.data.repository

import com.jmyentaku.app.data.model.VoiceActor
import com.jmyentaku.app.data.network.RetrofitInstance

class VoiceActorRepository {

    suspend fun getTopVoiceActors(): List<VoiceActor> {

        return RetrofitInstance.api
            .getTopVoiceActors()
            .data
    }
}