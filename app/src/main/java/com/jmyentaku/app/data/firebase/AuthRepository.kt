package com.jmyentaku.app.data.firebase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    suspend fun register(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            val result = auth.createUserWithEmailAndPassword(
                email,
                password
            ).await() //crea el usuario en firebase auth

            val userId = result.user?.uid
                ?: throw Exception("No se pudo obtener el UID") //crea automaticamente user

            val userData = hashMapOf(
                "email" to email,
                "createdAt" to System.currentTimeMillis()
            )

            db.collection("users")
                .document(userId)
                .set(userData)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            auth.signInWithEmailAndPassword(
                email,
                password
            ).await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    fun logout() {

        auth.signOut()
    }

    fun isUserLogged(): Boolean {

        return auth.currentUser != null
    }
}