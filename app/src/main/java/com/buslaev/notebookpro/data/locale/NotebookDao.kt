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

    @Query("UPDATE important_urgent_tasks SET done = :done WHERE id = :id")
    suspend fun updateImportantUrgentTask(id:Int, done:Int)

    /*
        Important Not Urgent
     */

    @Query("SELECT task.id,task.title,task.startDate,task.startTime,task.completionDate,task.completionTime,task.repeats,task.categoryId,ct.categoryTitle,task.done FROM important_not_urgent_tasks task INNER JOIN category_table as ct ON ct.categoryId = task.categoryId")
    fun getImportantNotUrgentTasks(): Flow<List<TaskDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImportantNotUrgentTask(value: ImportantNotUrgentTask)

    @Delete
    suspend fun deleteImportantNotUrgentTask(value: ImportantNotUrgentTask)

    @Query("UPDATE important_not_urgent_tasks SET done = :done WHERE id = :id")
    suspend fun updateImportantNotUrgentTask(id:Int, done:Int)

    /*
        Not Important Urgent
    */

    @Query("SELECT task.id,task.title,task.startDate,task.startTime,task.completionDate,task.completionTime,task.repeats,task.categoryId,ct.categoryTitle,task.done FROM not_important_urgent_tasks task INNER JOIN category_table as ct ON ct.categoryId = task.categoryId")
    fun getNotImportantUrgentTasks(): Flow<List<TaskDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotImportantUrgentTask(value: NotImportantUrgentTask)

    @Delete
    suspend fun deleteNotImportantUrgentTask(value: NotImportantUrgentTask)

    @Query("UPDATE important_not_urgent_tasks SET done = :done WHERE id = :id")
    suspend fun updateNotImportantUrgentTask(id:Int, done:Int)

    /*
        Not Important Not Urgent
    */

    @Query("SELECT task.id,task.title,task.startDate,task.startTime,task.completionDate,task.completionTime,task.repeats,task.categoryId,ct.categoryTitle,task.done FROM not_important_not_urgent_tasks task INNER JOIN category_table as ct ON ct.categoryId = task.categoryId")
    fun getNotImportantNotUrgentTasks(): Flow<List<TaskDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotImportantNotUrgentTask(value: NotImportantNotUrgentTask)

    @Delete
    suspend fun deleteNotImportantNotUrgentTask(value: NotImportantNotUrgentTask)

    @Query("UPDATE not_important_not_urgent_tasks SET done = :done WHERE id = :id")
    suspend fun updateNotImportantNotUrgentTask(id:Int, done:Int)

    /*
        Category
     */
    @Query("SELECT * FROM category_table")
    fun getCategory(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(value: Category)

    @Delete
    suspend fun deleteCategory(value: Category)


    /*
    Delete all tasks
     */
    @Query("DELETE FROM important_urgent_tasks")
    suspend fun deleteAlliu()
    @Query("DELETE FROM important_not_urgent_tasks")
    suspend fun deleteAllinu()
    @Query("DELETE FROM not_important_urgent_tasks")
    suspend fun deleteAllniu()
    @Query("DELETE FROM not_important_not_urgent_tasks")
    suspend fun deleteAllninu()
}