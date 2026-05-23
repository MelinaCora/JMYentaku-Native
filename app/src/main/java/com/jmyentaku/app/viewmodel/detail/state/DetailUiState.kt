package com.jmyentaku.app.viewmodel.detail.state

import com.jmyentaku.app.data.model.AnimeDetail
import com.jmyentaku.app.data.model.MangaDetail
import com.jmyentaku.app.data.model.VoiceActorDetail

data class DetailUiState(
    val isLoading: Boolean = false,
    val animeDetail: AnimeDetail? = null,
    val mangaDetail: MangaDetail? = null,
    val voiceActorDetail: VoiceActorDetail? = null,
    val error: String? = null
)