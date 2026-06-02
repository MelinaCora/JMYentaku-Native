package com.jmyentaku.app.data.preferences

import android.content.Context

object UserPreferences {

    private const val PREF = "user_data"
    private const val LAST_ACTIVITY = "last_activity"

    fun updateActivity(context: Context) {

        val prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

        prefs.edit()
            .putLong(LAST_ACTIVITY, System.currentTimeMillis())
            .apply()
    }

    fun getLastActivity(context: Context): Long {

        val prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

        return prefs.getLong(LAST_ACTIVITY, 0L)
    }
}