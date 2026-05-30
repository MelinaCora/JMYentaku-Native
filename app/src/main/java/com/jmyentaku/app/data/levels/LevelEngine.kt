package com.jmyentaku.app.data.levels

import com.jmyentaku.app.data.model.Level

object LevelEngine {

    fun calculateLevel(

        completedCount: Int,

        watchingCount: Int,

        plannedCount: Int,

        achievementCount: Int,

        currentStreak: Int

    ): Level {

        val xp =

            (completedCount * 100) +
                    (watchingCount * 25) +
                    (plannedCount * 10) +
                    (achievementCount * 50) +
                    (currentStreak * 15)

        val level =
            (xp / 1000) + 1

        val xpForNextLevel =
            (level * 1000)

        return Level(

            level = level,

            currentXp = xp,

            xpForNextLevel = xpForNextLevel
        )
    }

}