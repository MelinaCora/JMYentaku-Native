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

    suspend fun getActivities(): List<UserActivity> {

        val userId =
            auth.currentUser?.uid
                ?: return emptyList()

        val snapshot = db.collection("users")
            .document(userId)
            .collection("activities")
            .get()
            .await()

        return snapshot.documents.mapNotNull {

            it.toObject(UserActivity::class.java)
        }
    }
}