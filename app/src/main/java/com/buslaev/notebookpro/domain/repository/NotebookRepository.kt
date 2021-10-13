package com.buslaev.notebookpro.domain.repository

import com.buslaev.notebookpro.data.locale.model.*
import kotlinx.coroutines.flow.Flow

interface NotebookRepository {

    fun getImportantUrgentTasks(): Flow<List<TaskDto>>
    suspend fun insertImportantUrgentTask(task: ImportantUrgentTask)
    suspend fun deleteImportantUrgentTask(task: ImportantUrgentTask)

    fun getImportantNotUrgentTasks(): Flow<List<TaskDto>>
    suspend fun insertImportantNotUrgentTask(task: ImportantNotUrgentTask)
    suspend fun deleteImportantNotUrgentTask(task: ImportantNotUrgentTask)

    fun getNotImportantUrgentTasks(): Flow<List<TaskDto>>
    suspend fun insertNotImportantUrgentTask(task: NotImportantUrgentTask)
    suspend fun deleteNotImportantUrgentTask(task: NotImportantUrgentTask)

    fun getNotImportantNotUrgentTasks(): Flow<List<TaskDto>>
    suspend fun insertNotImportantNotUrgentTask(task: NotImportantNotUrgentTask)
    suspend fun deleteNotImportantNotUrgentTask(task: NotImportantNotUrgentTask)

    fun getCategories(): Flow<List<Category>>
    suspend fun insertCategory(category: Category)
    suspend fun deleteCategory(category: Category)
}