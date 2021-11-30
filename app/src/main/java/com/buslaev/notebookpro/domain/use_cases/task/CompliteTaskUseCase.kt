package com.buslaev.notebookpro.domain.use_cases.task

import com.buslaev.notebookpro.data.locale.model.ImportantNotUrgentTask
import com.buslaev.notebookpro.data.locale.model.ImportantUrgentTask
import com.buslaev.notebookpro.data.locale.model.NotImportantNotUrgentTask
import com.buslaev.notebookpro.data.locale.model.NotImportantUrgentTask
import com.buslaev.notebookpro.domain.repository.NotebookRepository
import javax.inject.Inject

class CompliteTaskUseCase @Inject constructor(
    private val repository: NotebookRepository
) {
    suspend fun compliteImportantUrgentTask(task: ImportantUrgentTask) {
        task.id?.let { repository.updateImportantUrgentTask(it, task.done) }
    }

    suspend fun compliteImportantNotUrgentTask(task: ImportantNotUrgentTask) {
        task.id?.let { repository.updateImportantNotUrgentTask(it, task.done) }
    }

    suspend fun compliteNotImportantUrgentTask(task: NotImportantUrgentTask) {
        task.id?.let { repository.updateNotImportantUrgentTask(it, task.done) }
    }

    suspend fun compliteNotImportantNotUrgentTask(task: NotImportantNotUrgentTask) {
        task.id?.let { repository.updateNotImportantNotUrgentTask(it, task.done) }
    }
}