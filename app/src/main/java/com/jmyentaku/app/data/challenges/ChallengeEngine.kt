package com.jmyentaku.app.data.challenges

import com.jmyentaku.app.data.model.Challenge

object ChallengeEngine {

    fun calculateChallenges(

        completedCount: Int,

        watchingCount: Int,

        plannedCount: Int

    ): List<Challenge> {

        return listOf(

            Challenge(

                id = "watching_3",

                title = "Active Viewer",

                description = "Have 3 anime in progress",

                progress = watchingCount,

                target = 3,

                completed = watchingCount >= 3
            ),

            Challenge(

                id = "completed_5",

                title = "Anime Finisher",

                description = "Complete 5 anime",

                progress = completedCount,

                target = 5,

                completed = completedCount >= 5
            ),

            Challenge(

                id = "planned_10",

                title = "Future Planner",

                description = "Add 10 anime to planned",

                progress = plannedCount,

                target = 10,

                completed = plannedCount >= 10
            )
        )
    }
}