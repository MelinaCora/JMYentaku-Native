package com.jmyentaku.app.data.model

data class VoiceActor(
    val mal_id: Int,
    val name: String,
    val favorites: Int,
    val images: VoiceActorImages
)

data class VoiceActorImages(
    val jpg: VoiceActorImageUrl
)

data class VoiceActorImageUrl(
    val image_url: String
)