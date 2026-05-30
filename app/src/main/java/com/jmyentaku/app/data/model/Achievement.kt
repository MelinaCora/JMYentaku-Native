package com.jmyentaku.app.data.model

data class Achievement(

    val id: String,

    val title: String,

    val description: String,

    val unlocked: Boolean,

    val progress: Int,

    val target: Int
)