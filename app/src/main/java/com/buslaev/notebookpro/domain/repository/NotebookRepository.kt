package com.buslaev.notebookpro.domain.repository

import com.buslaev.notebookpro.data.locale.model.*
import kotlinx.coroutines.flow.Flow

interface NotebookRepository {

    fun getImportantUrgentTasks(): Flow<List<TaskDto>>
    suspend fun insertImportantUrgentTask(task: ImportantUrgentTask)
    suspend fun deleteImportantUrgentTask(task: ImportantUrgentTask)
    suspend fun updateImportantUrgentTask(id:Int,done:Int)

    fun getImportantNotUrgentTasks(): Flow<List<TaskDto>>
    suspend fun insertImportantNotUrgentTask(task: ImportantNotUrgentTask)
    suspend fun deleteImportantNotUrgentTask(task: ImportantNotUrgentTask)
    suspend fun updateImportantNotUrgentTask(id:Int,done:Int)

    fun getNotImportantUrgentTasks(): Flow<List<TaskDto>>
    suspend fun insertNotImportantUrgentTask(task: NotImportantUrgentTask)
    suspend fun deleteNotImportantUrgentTask(task: NotImportantUrgentTask)
    suspend fun updateNotImportantUrgentTask(id:Int,done:Int)

    fun getNotImportantNotUrgentTasks(): Flow<List<TaskDto>>
    suspend fun insertNotImportantNotUrgentTask(task: NotImportantNotUrgentTask)
    suspend fun deleteNotImportantNotUrgentTask(task: NotImportantNotUrgentTask)
    suspend fun updateNotImportantNotUrgentTask(id:Int,done:Int)

    fun getCategories(): Flow<List<Category>>
    suspend fun insertCategory(category: Category)
    suspend fun deleteCategory(category: Category)

    suspend fun deleteAlliu()
    suspend fun deleteAllinu()
    suspend fun deleteAllniu()
    suspend fun deleteAllninu()
}