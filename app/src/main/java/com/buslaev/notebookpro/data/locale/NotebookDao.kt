package com.buslaev.notebookpro.data.locale

import androidx.room.*
import com.buslaev.notebookpro.data.locale.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotebookDao {

    /*
    Important Urgent
     */
    @Query("SELECT task.id,task.title,task.startDate,task.startTime,task.completionDate,task.completionTime,task.repeats,task.categoryId,ct.categoryTitle,task.done FROM important_urgent_tasks task INNER JOIN category_table as ct ON ct.categoryId = task.categoryId")
    fun getImportantUrgentTasks(): Flow<List<TaskDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImportantUrgentTask(value: ImportantUrgentTask)

    @Delete
    suspend fun deleteImportantUrgentTask(value: ImportantUrgentTask)

    /*
        Important Not Urgent
     */

    @Query("SELECT task.id,task.title,task.startDate,task.startTime,task.completionDate,task.completionTime,task.repeats,task.categoryId,ct.categoryTitle,task.done FROM important_not_urgent_tasks task INNER JOIN category_table as ct ON ct.categoryId = task.categoryId")
    fun getImportantNotUrgentTasks(): Flow<List<TaskDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImportantNotUrgentTask(value: ImportantNotUrgentTask)

    @Delete
    suspend fun deleteImportantNotUrgentTask(value: ImportantNotUrgentTask)

    /*
        Not Important Urgent
    */

    @Query("SELECT task.id,task.title,task.startDate,task.startTime,task.completionDate,task.completionTime,task.repeats,task.categoryId,ct.categoryTitle,task.done FROM not_important_urgent_tasks task INNER JOIN category_table as ct ON ct.categoryId = task.categoryId")
    fun getNotImportantUrgentTasks(): Flow<List<TaskDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotImportantUrgentTask(value: NotImportantUrgentTask)

    @Delete
    suspend fun deleteNotImportantUrgentTask(value: NotImportantUrgentTask)

    /*
        Not Important Not Urgent
    */

    @Query("SELECT task.id,task.title,task.startDate,task.startTime,task.completionDate,task.completionTime,task.repeats,task.categoryId,ct.categoryTitle,task.done FROM not_important_not_urgent_tasks task INNER JOIN category_table as ct ON ct.categoryId = task.categoryId")
    fun getNotImportantNotUrgentTasks(): Flow<List<TaskDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotImportantNotUrgentTask(value: NotImportantNotUrgentTask)

    @Delete
    suspend fun deleteNotImportantNotUrgentTask(value: NotImportantNotUrgentTask)

    /*
        Category
     */
    @Query("SELECT * FROM category_table")
    fun getCategory(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(value: Category)

    @Delete
    suspend fun deleteCategory(value: Category)

}