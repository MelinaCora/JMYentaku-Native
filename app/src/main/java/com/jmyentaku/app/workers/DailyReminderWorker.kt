package com.jmyentaku.app.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jmyentaku.app.notifications.NotificationHelper

class DailyReminderWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        NotificationHelper.show(
            applicationContext,
            "Tu anime te espera 🔥",
            "Entrá hoy para mantener tu progreso"
        )

        return Result.success()
    }
}