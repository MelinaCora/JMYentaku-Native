package com.jmyentaku.app

import com.jmyentaku.app.ui.navigation.NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jmyentaku.app.ui.theme.JMYentakuTheme
import com.jmyentaku.app.notifications.NotificationChannels

import com.jmyentaku.app.workers.scheduleDailyReminder
import com.jmyentaku.app.workers.scheduleStreakCheck
import com.jmyentaku.app.notifications.NotificationHelper

import android.os.Build

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                100
            )
        }

        // 🔔 Notificaciones
        NotificationChannels.create(this)

        // ⏰ WorkManager (recordatorios + racha)
        scheduleDailyReminder(this)
        scheduleStreakCheck(this)

        setContent {
            JMYentakuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavGraph()
                }
            }
        }
        NotificationHelper.show(
            this,
            "TEST AUTOMÁTICO 🔔",
            "Se ejecuta al abrir la app"
        )
    }


}