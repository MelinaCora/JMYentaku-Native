package com.jmyentaku.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.jmyentaku.app.data.model.ManualManga
import kotlinx.coroutines.tasks.await

class ManualMangaRepository {

    private val firestore =
        FirebaseFirestore.getInstance()

    suspend fun saveManualManga(
        manga: ManualManga
    ) {

        firestore
            .collection("manual_mangas")
            .add(manga)
            .await()
    }
}