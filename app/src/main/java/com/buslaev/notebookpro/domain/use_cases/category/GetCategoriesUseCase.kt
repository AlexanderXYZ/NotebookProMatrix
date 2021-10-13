package com.buslaev.notebookpro.domain.use_cases.category

import com.buslaev.notebookpro.data.locale.model.Category
import com.buslaev.notebookpro.domain.repository.NotebookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
        private val repository: NotebookRepository
) {

    operator fun invoke(): Flow<List<Category>> {
        return repository.getCategories()
    }
}