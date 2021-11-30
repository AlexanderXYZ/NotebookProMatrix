package com.buslaev.notebookpro.data.locale.repository

import com.buslaev.notebookpro.data.locale.NotebookDao
import com.buslaev.notebookpro.data.locale.model.*
import com.buslaev.notebookpro.domain.repository.NotebookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotebookRepositoryImpl @Inject constructor(
    private val dao: NotebookDao,
) : NotebookRepository {

    //  Important Urgent

    override fun getImportantUrgentTasks(): Flow<List<TaskDto>> {
        return dao.getImportantUrgentTasks()
    }

    override suspend fun insertImportantUrgentTask(task: ImportantUrgentTask) {
        dao.insertImportantUrgentTask(task)
    }

    override suspend fun deleteImportantUrgentTask(task: ImportantUrgentTask) {
        dao.deleteImportantUrgentTask(task)
    }

    override suspend fun updateImportantUrgentTask(id: Int, done: Int) {
        dao.updateImportantUrgentTask(id, done)
    }

    //  Important Not Urgent

    override fun getImportantNotUrgentTasks(): Flow<List<TaskDto>> {
        return dao.getImportantNotUrgentTasks()
    }

    override suspend fun insertImportantNotUrgentTask(task: ImportantNotUrgentTask) {
        dao.insertImportantNotUrgentTask(task)
    }

    override suspend fun deleteImportantNotUrgentTask(task: ImportantNotUrgentTask) {
        dao.deleteImportantNotUrgentTask(task)
    }

    override suspend fun updateImportantNotUrgentTask(id: Int, done: Int) {
        dao.updateImportantNotUrgentTask(id, done)
    }

    //  Not Important Urgent

    override fun getNotImportantUrgentTasks(): Flow<List<TaskDto>> {
        return dao.getNotImportantUrgentTasks()
    }

    override suspend fun insertNotImportantUrgentTask(task: NotImportantUrgentTask) {
        dao.insertNotImportantUrgentTask(task)
    }

    override suspend fun deleteNotImportantUrgentTask(task: NotImportantUrgentTask) {
        dao.deleteNotImportantUrgentTask(task)
    }

    override suspend fun updateNotImportantUrgentTask(id: Int, done: Int) {
        dao.updateNotImportantUrgentTask(id, done)
    }

    //  Not Important Not Urgent

    override fun getNotImportantNotUrgentTasks(): Flow<List<TaskDto>> {
        return dao.getNotImportantNotUrgentTasks()
    }

    override suspend fun insertNotImportantNotUrgentTask(task: NotImportantNotUrgentTask) {
        dao.insertNotImportantNotUrgentTask(task)
    }

    override suspend fun deleteNotImportantNotUrgentTask(task: NotImportantNotUrgentTask) {
        deleteNotImportantNotUrgentTask(task)
    }

    override suspend fun updateNotImportantNotUrgentTask(id: Int, done: Int) {
        dao.updateNotImportantNotUrgentTask(id, done)
    }

    //  Category

    override fun getCategories(): Flow<List<Category>> {
        return dao.getCategory()
    }

    override suspend fun insertCategory(category: Category) {
        dao.insertCategory(category)
    }

    override suspend fun deleteCategory(category: Category) {
        dao.deleteCategory(category)
    }

    override suspend fun deleteAlliu() {
        dao.deleteAlliu()
    }

    override suspend fun deleteAllinu() {
        dao.deleteAllinu()
    }

    override suspend fun deleteAllniu() {
        dao.deleteAllniu()
    }

    override suspend fun deleteAllninu() {
        dao.deleteAllninu()
    }

}