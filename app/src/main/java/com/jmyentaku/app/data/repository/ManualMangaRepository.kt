package com.jmyentaku.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jmyentaku.app.data.model.ManualManga
import kotlinx.coroutines.tasks.await

class ManualMangaRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun getUserId(): String {
        return auth.currentUser?.uid
            ?: throw Exception("User not logged in")
    }

    // SAVE por usuario
    suspend fun saveManualManga(manga: ManualManga) {

        val uid = getUserId()

        // CREAR docRef correctamente
        val docRef = firestore
            .collection("users")
            .document(uid)
            .collection("added_manga")
            .document()

        //agregar ID al objeto
        val mangaWithId = manga.copy(id = docRef.id)

        //guardar
        docRef.set(mangaWithId).await()
    }

    // GET por usuario
    suspend fun getManualMangas(): List<ManualManga> {

        val uid = getUserId()

        val result = firestore
            .collection("users")
            .document(uid)
            .collection("added_manga")
            .get()
            .await()

        return result.documents.mapNotNull {
            it.toObject(ManualManga::class.java)
        }
    }

    // UPDATE por usuario
    suspend fun updateManga(manga: ManualManga) {

        val uid = getUserId()

        firestore
            .collection("users")
            .document(uid)
            .collection("added_manga")
            .document(manga.id)
            .set(manga)
            .await()
    }
}