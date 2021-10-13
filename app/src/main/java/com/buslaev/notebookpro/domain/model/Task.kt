package com.buslaev.notebookpro.domain.model

data class Task(
    var id: Int? = null,
    var title: String = "",
    var startDate: String = "",
    var startTime: String = "",
    var completionDate: String = "",
    var completionTime: String = "",
    var repeats: String = "",
    var categoryId: Int? = null,
    var categoryTitle: String = "",
    var done: Boolean = false
)
