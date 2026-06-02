package com.jmyentaku.app.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jmyentaku.app.notifications.NotificationHelper

class StreakCheckWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        val prefs = applicationContext.getSharedPreferences("user_data", Context.MODE_PRIVATE)

        val last = prefs.getLong("last_activity", 0L)
        val now = System.currentTimeMillis()

        val diffHours = (now - last) / (1000 * 60 * 60)

        if (diffHours >= 20) {

            NotificationHelper.show(
                applicationContext,
                "⚠️ ¿En serio vas a perder tu racha?",
                "Hace rato que no avanzás en tus animes"
            )
        }

        return Result.success()
    }
}