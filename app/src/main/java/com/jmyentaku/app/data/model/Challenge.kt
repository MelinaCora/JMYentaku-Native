package com.jmyentaku.app.data.model

data class Challenge (

    val id: String,

    val title: String,

    val description: String,

    val progress: Int,

    val target: Int,

    val completed: Boolean
)