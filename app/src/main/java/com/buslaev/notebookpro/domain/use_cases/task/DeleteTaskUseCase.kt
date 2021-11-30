package com.buslaev.notebookpro.domain.use_cases.task

import com.buslaev.notebookpro.data.locale.model.*
import com.buslaev.notebookpro.domain.model.Task
import com.buslaev.notebookpro.domain.repository.NotebookRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: NotebookRepository,
) {
    suspend fun deleteImportantUrgentTask(task: Task) = coroutineScope {
        val dbTask = ImportantUrgentTask(
            id = task.id,
            title = task.title,
            startDate = task.startDate,
            startTime = task.startTime,
            completionDate = task.completionDate,
            completionTime = task.completionTime,
            repeats = task.repeats,
            categoryId = task.categoryId
        )
        repository.deleteImportantUrgentTask(dbTask)
    }

    suspend fun deleteImportantNotUrgentTask(task: Task) = coroutineScope {
        val dbTask = ImportantNotUrgentTask(
            id = task.id,
            title = task.title,
            startDate = task.startDate,
            startTime = task.startTime,
            completionDate = task.completionDate,
            completionTime = task.completionTime,
            repeats = task.repeats,
            categoryId = task.categoryId
        )
        repository.deleteImportantNotUrgentTask(dbTask)
    }

    suspend fun deleteNotImportantUrgentTask(task: Task) = coroutineScope {
        val dbTask = NotImportantUrgentTask(
            id = task.id,
            title = task.title,
            startDate = task.startDate,
            startTime = task.startTime,
            completionDate = task.completionDate,
            completionTime = task.completionTime,
            repeats = task.repeats,
            categoryId = task.categoryId
        )
        repository.deleteNotImportantUrgentTask(dbTask)
    }

    suspend fun deleteNotImportantNotUrgentTask(task: Task) = coroutineScope {
        val dbTask = NotImportantNotUrgentTask(
            id = task.id,
            title = task.title,
            startDate = task.startDate,
            startTime = task.startTime,
            completionDate = task.completionDate,
            completionTime = task.completionTime,
            repeats = task.repeats,
            categoryId = task.categoryId
        )
        repository.deleteNotImportantNotUrgentTask(dbTask)
    }
}