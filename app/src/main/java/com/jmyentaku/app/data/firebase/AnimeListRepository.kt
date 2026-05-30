package com.jmyentaku.app.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.jmyentaku.app.data.model.UserAnime
import kotlinx.coroutines.tasks.await

class AnimeListRepository {

    private val auth = FirebaseAuth.getInstance()

    private val db = FirebaseFirestore.getInstance()

    // =========================
    // Guardar o actualizar anime
    // =========================
    suspend fun saveAnime(
        anime: UserAnime
    ): Result<Unit> {

        return try {

            val userId = auth.currentUser?.uid
                ?: throw Exception("Usuario no autenticado")

            db.collection("users")
                .document(userId)
                .collection("anime_lists")
                .document(anime.animeId.toString())
                .set(anime)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    // =========================
    // Eliminar anime
    // =========================
    suspend fun deleteAnime(
        animeId: Int
    ): Result<Unit> {

        return try {

            val userId = auth.currentUser?.uid
                ?: throw Exception("Usuario no autenticado")

            db.collection("users")
                .document(userId)
                .collection("anime_lists")
                .document(animeId.toString())
                .delete()
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    // =========================
    // Obtener animes por estado
    // =========================
    suspend fun getAnimeByStatus(
        status: String
    ): Result<List<UserAnime>> {

        return try {

            val userId = auth.currentUser?.uid
                ?: throw Exception("Usuario no autenticado")

            val result = db.collection("users")
                .document(userId)
                .collection("anime_lists")
                .whereEqualTo("status", status)
                .get()
                .await()

            val animeList = result.documents.mapNotNull {

                it.toObject<UserAnime>()
            }

            Result.success(animeList)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun getAllUserAnime(): Result<List<UserAnime>> {

        return try {

            val userId =
                auth.currentUser?.uid
                    ?: throw Exception("User not authenticated")

            val snapshot =
                firestore
                    .collection("users")
                    .document(userId)
                    .collection("anime_lists")
                    .get()
                    .await()

            val animeList =
                snapshot.documents.mapNotNull {

                    it.toObject(UserAnime::class.java)
                }

            Result.success(animeList)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }
}