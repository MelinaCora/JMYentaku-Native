package com.jmyentaku.app.data.streaks

import com.jmyentaku.app.data.model.Streak
import com.jmyentaku.app.data.model.UserActivity
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

object StreakEngine {

    fun calculateStreak(

        activities: List<UserActivity>

    ): Streak {

        if (activities.isEmpty()) {

            return Streak()
        }

        val days = activities
            .map {

                Instant.ofEpochMilli(
                    it.timestamp
                )
                    .atZone(
                        ZoneId.systemDefault()
                    )
                    .toLocalDate()
            }
            .distinct()
            .sortedDescending()

        // =========================
        // Current Streak
        // =========================

        var current = 1

        for (i in 0 until days.size - 1) {

            if (
                days[i]
                    .minusDays(1)
                == days[i + 1]
            ) {

                current++

            } else {

                break
            }
        }

        // =========================
        // Longest Streak
        // =========================

        var longest = 1

        var tempStreak = 1

        for (i in 0 until days.size - 1) {

            if (
                days[i]
                    .minusDays(1)
                == days[i + 1]
            ) {

                tempStreak++

                if (tempStreak > longest) {

                    longest = tempStreak
                }

            } else {

                tempStreak = 1
            }
        }

        return Streak(

            currentStreak = current,

            longestStreak = longest,

            activeDays = days.size
        )
    }

}