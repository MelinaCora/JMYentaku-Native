package com.jmyentaku.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.jmyentaku.app.data.model.ManualManga
import kotlinx.coroutines.tasks.await

class ManualMangaRepository {

    private val firestore = FirebaseFirestore.getInstance()

    // SAVE manga manual
    suspend fun saveManualManga(manga: ManualManga) {
        firestore
            .collection("added_manga")
            .add(manga)
            .await()
    }

    // GET mangas
    suspend fun getManualMangas(): List<ManualManga> {
        val result = firestore
            .collection("added_manga")
            .get()
            .await()

        return result.documents.mapNotNull {
            it.toObject(ManualManga::class.java)
        }
    }
}