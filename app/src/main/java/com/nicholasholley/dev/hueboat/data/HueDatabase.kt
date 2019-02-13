package com.nicholasholley.dev.hueboat.data

import androidx.room.*
import com.nicholasholley.dev.hueboat.data.models.*

@Database(
        entities = [
            Configuration::class,
            Group::class,
            Light::class,
            Rule::class,
            Scene::class,
            Schedule::class
        ],
        views = [],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun configurationDao(): ConfigurationDao
    abstract fun groupDao(): GroupDao
    abstract fun lightDao(): LightDao
    abstract fun ruleDao(): RuleDao
    abstract fun sceneDao(): SceneDao
    abstract fun scheduleDao(): ScheduleDao
}
