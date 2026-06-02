package com.jmyentaku.app.workers

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun scheduleDailyReminder(context: Context) {

    val request = PeriodicWorkRequestBuilder<DailyReminderWorker>(
        1, TimeUnit.DAYS
    ).build()

    WorkManager.getInstance(context)
        .enqueueUniquePeriodicWork(
            "daily_reminder",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
}

fun scheduleStreakCheck(context: Context) {

    val request = PeriodicWorkRequestBuilder<StreakCheckWorker>(
        6, TimeUnit.HOURS
    ).build()

    WorkManager.getInstance(context)
        .enqueueUniquePeriodicWork(
            "streak_check",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
}