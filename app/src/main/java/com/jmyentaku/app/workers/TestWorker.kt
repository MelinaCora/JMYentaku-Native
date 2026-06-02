package com.jmyentaku.app.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jmyentaku.app.notifications.NotificationHelper

class TestWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        NotificationHelper.show(
            applicationContext,
            "WORKMANAGER TEST 🔥",
            "Esto se ejecutó desde WorkManager"
        )

        return Result.success()
    }
}