package com.buslaev.notebookpro.data.locale.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category(
    @PrimaryKey
    var categoryId: Int? = null,
    var categoryTitle: String,
)
