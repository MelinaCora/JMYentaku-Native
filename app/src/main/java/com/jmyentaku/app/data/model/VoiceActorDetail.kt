package com.jmyentaku.app.data.model

data class VoiceActorDetail(
    val mal_id: Int,
    val name: String,
    val given_name: String?,
    val family_name: String?,
    val favorites: Int,
    val about: String?,
    val images: VoiceActorImages
)