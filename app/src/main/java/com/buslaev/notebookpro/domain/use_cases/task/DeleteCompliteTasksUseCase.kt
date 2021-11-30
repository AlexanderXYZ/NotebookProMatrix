package com.buslaev.notebookpro.domain.use_cases.task

import com.buslaev.notebookpro.domain.repository.NotebookRepository
import javax.inject.Inject

class DeleteCompliteTasksUseCase @Inject constructor(
    private val repository: NotebookRepository
) {
    suspend operator fun invoke(){

    }
}