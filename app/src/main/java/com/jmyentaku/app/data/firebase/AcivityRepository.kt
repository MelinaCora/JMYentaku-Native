package com.jmyentaku.app.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jmyentaku.app.data.model.UserActivity
import kotlinx.coroutines.tasks.await

class ActivityRepository {

    private val auth =
        FirebaseAuth.getInstance()

    private val db =
        FirebaseFirestore.getInstance()

    suspend fun registerActivity(

        activity: UserActivity
    ) {

        val userId =
            auth.currentUser?.uid
                ?: return

        db.collection("users")
            .document(userId)
            .collection("activities")
            .add(activity)
            .await()
    }
}