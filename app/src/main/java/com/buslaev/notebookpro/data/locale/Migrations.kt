package com.buslaev.notebookpro.data.locale

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `important_urgent_tasks` (`id` INTEGER, `title` TEXT NOT NULL, `startDate` TEXT NOT NULL, `startTime` TEXT NOT NULL, `completionDate` TEXT NOT NULL, `completionTime` TEXT NOT NULL, `repeats` TEXT NOT NULL, `categoryId` INTEGER, `done` INTEGER NOT NULL, PRIMARY KEY(`id`))")
            database.execSQL("CREATE TABLE `important_not_urgent_tasks` (`id` INTEGER, `title` TEXT NOT NULL, `startDate` TEXT NOT NULL, `startTime` TEXT NOT NULL, `completionDate` TEXT NOT NULL, `completionTime` TEXT NOT NULL, `repeats` TEXT NOT NULL, `categoryId` INTEGER, `done` INTEGER NOT NULL, PRIMARY KEY(`id`))")
            database.execSQL("CREATE TABLE `not_important_urgent_tasks` (`id` INTEGER, `title` TEXT NOT NULL, `startDate` TEXT NOT NULL, `startTime` TEXT NOT NULL, `completionDate` TEXT NOT NULL, `completionTime` TEXT NOT NULL, `repeats` TEXT NOT NULL, `categoryId` INTEGER, `done` INTEGER NOT NULL, PRIMARY KEY(`id`))")
            database.execSQL("CREATE TABLE `not_important_not_urgent_tasks` (`id` INTEGER, `title` TEXT NOT NULL, `startDate` TEXT NOT NULL, `startTime` TEXT NOT NULL, `completionDate` TEXT NOT NULL, `completionTime` TEXT NOT NULL, `repeats` TEXT NOT NULL, `categoryId` INTEGER, `done` INTEGER NOT NULL, PRIMARY KEY(`id`))")
            database.execSQL("CREATE TABLE `category_table` (`id` INTEGER,`title` TEXT NOT NULL,PRIMARY KEY (`id`))")
        }
    }
    val MIGRATION_2_3 = object : Migration(2,3){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("DROP TABLE IF EXISTS `list_tasks_table`")
            database.execSQL("DROP TABLE IF EXISTS `tasks_table`")
        }
    }

    val MIGRATION_3_4 = object : Migration(3,4){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE `category_table` RENAME COLUMN `id` TO `categoryId`")
            database.execSQL("ALTER TABLE `category_table` RENAME COLUMN `title` TO `categoryTitle`")
        }
    }
}