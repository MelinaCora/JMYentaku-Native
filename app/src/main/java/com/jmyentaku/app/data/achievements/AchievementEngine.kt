package com.jmyentaku.app.data.achievements

import com.jmyentaku.app.data.model.Achievement

object AchievementEngine {

    fun calculateAchievements(

        completedCount: Int,

        watchingCount: Int,

        plannedCount: Int

    ): List<Achievement> {

        return listOf(

            Achievement(
                id = "anime_beginner",
                title = "Anime Beginner",
                description = "Complete 5 anime",
                unlocked = completedCount >= 5,
                progress = completedCount,
                target = 5
            ),

            Achievement(
                id = "otaku_rising",
                title = "Otaku Rising",
                description = "Complete 25 anime",
                unlocked = completedCount >= 25,
                progress = completedCount,
                target = 25
            ),

            Achievement(
                id = "anime_master",
                title = "Anime Master",
                description = "Complete 100 anime",
                unlocked = completedCount >= 100,
                progress = completedCount,
                target = 100
            ),

            Achievement(
                id = "active_watcher",
                title = "Active Watcher",
                description = "Have 10 anime in progress",
                unlocked = watchingCount >= 10,
                progress = watchingCount,
                target = 10
            ),

            Achievement(
                id = "future_planner",
                title = "Future Planner",
                description = "Have 20 planned anime",
                unlocked = plannedCount >= 20,
                progress = plannedCount,
                target = 20
            )
        )
    }
}