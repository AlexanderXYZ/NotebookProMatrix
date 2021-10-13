package com.buslaev.notebookpro.presentation.add_task

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buslaev.notebookpro.common.noSeconds
import com.buslaev.notebookpro.domain.model.Task
import com.buslaev.notebookpro.domain.use_cases.category.GetCategoriesUseCase
import com.buslaev.notebookpro.domain.use_cases.task.InsertTaskUseCase
import com.buslaev.notebookpro.presentation.task_list.Tasks
import com.buslaev.notebookpro.presentation.task_list.Tasks.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
        private val insertTaskUseCase: InsertTaskUseCase,
        private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private var currentDate = ""
    private var currentTime = ""

    private var _categoryState = MutableStateFlow(CategoriesState())
    val categoryState: StateFlow<CategoriesState> = _categoryState

    init {
        setCurrentDateTime()
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect {
                if (it.isNullOrEmpty()) {
                    _categoryState.value = CategoriesState(empty = true)
                } else {
                    _categoryState.value = CategoriesState(categories = it)
                }
            }
        }

    }

    private fun setCurrentDateTime() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currentDate = LocalTime.now().noSeconds().toString()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currentTime = LocalDate.now().toString()
        }
    }

    fun insertTask(
            title: String,
            completionDate: String,
            completionTime: String,
            repeats: String,
            categoryId: Int? = null,
            tasks: Tasks,
    ) = viewModelScope.launch {
        val task = Task(
                title = title,
                startDate = currentDate,
                startTime = currentTime,
                completionDate = completionDate,
                completionTime = completionTime,
                repeats = repeats,
                categoryId = categoryId)
        insertTaskIntoDb(task = task, tasks = tasks)
    }

    private suspend fun insertTaskIntoDb(task: Task, tasks: Tasks) {
        when (tasks) {
            IMPORTANT_URGENT -> {
                insertTaskUseCase.insertImportantUrgentTask(task)
            }
            IMPORTANT_NOT_URGENT -> {
                insertTaskUseCase.insertImportantNotUrgentTask(task)
            }
            NOT_IMPORTANT_URGENT -> {
                insertTaskUseCase.insertNotImportantUrgentTask(task)
            }
            NOT_IMPORTANT_NOT_URGENT -> {
                insertTaskUseCase.insertNotImportantNotUrgentTask(task)
            }
        }
    }


}