package com.buslaev.notebookpro.data.locale.model

import com.buslaev.notebookpro.domain.model.Task


data class TaskDto(
    var id: Int? = null,
    var title: String = "",
    var startDate: String = "",
    var startTime: String = "",
    var completionDate: String = "",
    var completionTime: String = "",
    var repeats: String = "",
    var categoryId: Int? = null,
    var categoryTitle: String,
    var done: Int = 0
)

fun TaskDto.toTask(): Task {
    return Task(
        id = id,
        title = title,
        startDate = startDate,
        startTime = startTime,
        completionDate = completionDate,
        completionTime = completionTime,
        repeats = repeats,
        categoryId = categoryId,
        categoryTitle = categoryTitle,
        done = done != 0
    )
}
