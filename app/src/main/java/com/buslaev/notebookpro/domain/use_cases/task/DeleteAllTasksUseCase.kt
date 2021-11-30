package com.buslaev.notebookpro.domain.use_cases.task

import com.buslaev.notebookpro.domain.repository.NotebookRepository
import javax.inject.Inject

class DeleteAllTasksUseCase @Inject constructor(
    private val repository: NotebookRepository
) {
    suspend operator fun invoke(){
        repository.deleteAlliu()
        repository.deleteAllinu()
        repository.deleteAllniu()
        repository.deleteAllninu()
    }
}