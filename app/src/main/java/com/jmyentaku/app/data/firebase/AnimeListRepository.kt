package com.jmyentaku.app.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.jmyentaku.app.data.model.UserAnime
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.ListenerRegistration
import com.jmyentaku.app.data.model.UserActivity

class AnimeListRepository {

    private val activityRepository =
        ActivityRepository()

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

            // Registrar actividad del usuario
            activityRepository.registerActivity(

                UserActivity(

                    type = "anime_saved",

                    animeId = anime.animeId,

                    timestamp = System.currentTimeMillis()
                )
            )

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
    suspend fun getAnimeByStatus(status: String): Result<List<UserAnime>> {
        return try {
            val userId = auth.currentUser?.uid
                ?: throw Exception("Usuario no autenticado")

            val result = db.collection("users")
                .document(userId)
                .collection("anime_lists")
                .whereEqualTo("status", status)
                .get()
                .await()

            val animeList = result.documents.mapNotNull { doc ->
                val data = doc.data ?: return@mapNotNull null

                if (!data.containsKey("type")) {
                    val animeId = data["animeId"] as? Int ?: return@mapNotNull null
                    val title = data["title"] as? String ?: ""
                    val imageUrl = data["imageUrl"] as? String ?: ""
                    val oldStatus = data["status"] as? String ?: "planned"
                    val updatedAt = data["updatedAt"] as? Long ?: System.currentTimeMillis()

                    val updatedAnime = UserAnime(
                        animeId = animeId,
                        title = title,
                        imageUrl = imageUrl,
                        type = "anime",
                        status = oldStatus,
                        updatedAt = updatedAt
                    )

                    // Actualizar en Firestore automáticamente
                    doc.reference.set(updatedAnime)

                    updatedAnime
                } else {
                    doc.toObject<UserAnime>()
                }
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
                db
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

    fun observeUserAnime(

        onUpdate: (List<UserAnime>) -> Unit,

        onError: (Exception) -> Unit
    ): ListenerRegistration {

        val userId =
            auth.currentUser?.uid
                ?: throw Exception("Usuario no autenticado")

        return db
            .collection("users")
            .document(userId)
            .collection("anime_lists")
            .addSnapshotListener { snapshot, error ->

                if (error != null) {

                    onError(error)
                    return@addSnapshotListener
                }

                val animeList =
                    snapshot?.documents
                        ?.mapNotNull {

                            it.toObject(UserAnime::class.java)
                        }
                        ?: emptyList()

                onUpdate(animeList)
            }
    }

    suspend fun saveAnimeWithTotal(
        anime: UserAnime
    ): Result<Unit> {

        return saveAnime(anime)
    }
    suspend fun updateProgress(
        animeId: Int,
        newProgress: Int
    ): Result<Unit> {

        return try {

            val userId =
                auth.currentUser?.uid
                    ?: throw Exception("Usuario no autenticado")

            db.collection("users")
                .document(userId)
                .collection("anime_lists")
                .document(animeId.toString())
                .update(
                    "progress",
                    newProgress
                )
                .await()

            activityRepository.registerActivity(

                UserActivity(

                    type = "episode_watched",

                    animeId = animeId,

                    timestamp = System.currentTimeMillis()
                )
            )

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }
}