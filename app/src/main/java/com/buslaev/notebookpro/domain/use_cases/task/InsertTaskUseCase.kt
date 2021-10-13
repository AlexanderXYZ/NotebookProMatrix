package com.buslaev.notebookpro.domain.use_cases.task

import com.buslaev.notebookpro.data.locale.model.*
import com.buslaev.notebookpro.domain.model.Task
import com.buslaev.notebookpro.domain.repository.NotebookRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class InsertTaskUseCase @Inject constructor(
    private val repository: NotebookRepository,
) {
    suspend fun insertImportantUrgentTask(task: Task) = coroutineScope {
        val dbTask = ImportantUrgentTask(
            title = task.title,
            startDate = task.startDate,
            startTime = task.startTime,
            completionDate = task.completionDate,
            completionTime = task.completionTime,
            repeats = task.repeats,
            categoryId = task.categoryId
        )
        repository.insertImportantUrgentTask(dbTask)
    }

    suspend fun insertImportantNotUrgentTask(task: Task) = coroutineScope {
        val dbTask = ImportantNotUrgentTask(
            title = task.title,
            startDate = task.startDate,
            startTime = task.startTime,
            completionDate = task.completionDate,
            completionTime = task.completionTime,
            repeats = task.repeats,
            categoryId = task.categoryId
        )
        repository.insertImportantNotUrgentTask(dbTask)
    }

    suspend fun insertNotImportantUrgentTask(task: Task) = coroutineScope {
        val dbTask = NotImportantUrgentTask(
            title = task.title,
            startDate = task.startDate,
            startTime = task.startTime,
            completionDate = task.completionDate,
            completionTime = task.completionTime,
            repeats = task.repeats,
            categoryId = task.categoryId
        )
        repository.insertNotImportantUrgentTask(dbTask)
    }

    suspend fun insertNotImportantNotUrgentTask(task: Task) = coroutineScope {
        val dbTask = NotImportantNotUrgentTask(
            title = task.title,
            startDate = task.startDate,
            startTime = task.startTime,
            completionDate = task.completionDate,
            completionTime = task.completionTime,
            repeats = task.repeats,
            categoryId = task.categoryId
        )
        repository.insertNotImportantNotUrgentTask(dbTask)
    }
}