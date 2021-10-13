package com.buslaev.notebookpro.presentation.add_task

import com.buslaev.notebookpro.data.locale.model.Category

data class CategoriesState(
        var categories: List<Category> = emptyList(),
        var empty: Boolean = false
)
