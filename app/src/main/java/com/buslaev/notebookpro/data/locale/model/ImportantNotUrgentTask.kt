package com.buslaev.notebookpro.data.locale.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.buslaev.notebookpro.domain.model.Task

@Entity(tableName = "important_not_urgent_tasks")
data class ImportantNotUrgentTask(
    @PrimaryKey
    var id: Int? = null,
    var title: String = "",
    var startDate: String = "",
    var startTime: String = "",
    var completionDate: String = "",
    var completionTime: String = "",
    var repeats: String = "",
    var categoryId: Int? = null,
    var done: Int = 0
)
