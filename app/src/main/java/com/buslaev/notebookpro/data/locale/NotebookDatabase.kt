package com.buslaev.notebookpro.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import com.buslaev.notebookpro.data.locale.model.*

@Database(
    entities = [
        ImportantUrgentTask::class,
        ImportantNotUrgentTask::class,
        NotImportantUrgentTask::class,
        NotImportantNotUrgentTask::class,
        Category::class
    ],
    version = 4,
    exportSchema = true
)
abstract class NotebookDatabase : RoomDatabase() {
    abstract fun getDao(): NotebookDao
}