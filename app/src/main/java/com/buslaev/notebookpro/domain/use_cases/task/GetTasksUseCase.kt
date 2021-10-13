package com.buslaev.notebookpro.domain.use_cases.task

import com.buslaev.notebookpro.data.locale.model.*
import com.buslaev.notebookpro.domain.repository.NotebookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: NotebookRepository,
) {

    fun getImportantUrgentTasks(): Flow<List<TaskDto>> {
        return repository.getImportantUrgentTasks()
    }

    fun getImportantNotUrgentTasks(): Flow<List<TaskDto>> {
        return repository.getImportantNotUrgentTasks()
    }

    fun getNotImportantUrgentTasks(): Flow<List<TaskDto>> {
        return repository.getNotImportantUrgentTasks()
    }

    fun getNotImportantNotUrgentTasks(): Flow<List<TaskDto>> {
        return repository.getNotImportantNotUrgentTasks()
    }
}