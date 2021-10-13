package com.buslaev.notebookpro.presentation.task_list

import com.buslaev.notebookpro.domain.model.Task

data class TaskListState(
    var tasks: List<Task> = emptyList(),
    var empty: Boolean = true
)