package com.buslaev.notebookpro.presentation.task_list

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buslaev.notebookpro.data.locale.model.*
import com.buslaev.notebookpro.domain.model.Task
import com.buslaev.notebookpro.domain.use_cases.task.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val compliteTaskUseCase: CompliteTaskUseCase,
    private val deleteAllTasksUseCase: DeleteAllTasksUseCase,
    private val deleteCompliteTasksUseCase: DeleteCompliteTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val _stateImportantUrgent = MutableStateFlow(TaskListState())
    val stateImportantUrgent: StateFlow<TaskListState> = _stateImportantUrgent

    private val _stateImportantNotUrgent = MutableStateFlow(TaskListState())
    val stateImportantNotUrgent: StateFlow<TaskListState> = _stateImportantNotUrgent

    private val _stateNotImportantUrgent = MutableStateFlow(TaskListState())
    val stateNotImportantUrgent: StateFlow<TaskListState> = _stateNotImportantUrgent

    private val _stateNotImportantNotUrgent = MutableStateFlow(TaskListState())
    val stateNotImportantNotUrgent: StateFlow<TaskListState> = _stateNotImportantNotUrgent

    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            getTasksUseCase.getImportantUrgentTasks().collect { list ->
                _stateImportantUrgent.value =
                    TaskListState(tasks = list.map { it.toTask() }, empty = false)
            }
        }
        viewModelScope.launch {
            getTasksUseCase.getImportantNotUrgentTasks().collect { list ->
                _stateImportantNotUrgent.value =
                    TaskListState(tasks = list.map { it.toTask() }, empty = false)
            }
        }
        viewModelScope.launch {
            getTasksUseCase.getNotImportantUrgentTasks().collect { list ->
                _stateNotImportantUrgent.value =
                    TaskListState(tasks = list.map { it.toTask() }, empty = false)
            }
        }
        viewModelScope.launch {
            getTasksUseCase.getNotImportantNotUrgentTasks().collect { list ->
                _stateNotImportantNotUrgent.value =
                    TaskListState(tasks = list.map { it.toTask() }, empty = false)
            }
        }
    }

    // FIX ME
    fun filterList(list: List<Int>) {
    }

    fun sortByABC() {
        _stateImportantUrgent.value.tasks =
            _stateImportantUrgent.value.tasks.sortedBy { it.title }
        _stateImportantNotUrgent.value.tasks =
            _stateImportantNotUrgent.value.tasks.sortedBy { it.title }
        _stateNotImportantUrgent.value.tasks =
            _stateNotImportantUrgent.value.tasks.sortedBy { it.title }
        _stateNotImportantNotUrgent.value.tasks =
            _stateNotImportantNotUrgent.value.tasks.sortedBy { it.title }
    }

    fun sortByDateErlier() {
        _stateImportantUrgent.value.tasks =
            _stateImportantUrgent.value.tasks.sortedBy { it.startDate }
        _stateImportantNotUrgent.value.tasks =
            _stateImportantNotUrgent.value.tasks.sortedBy { it.startDate }
        _stateNotImportantUrgent.value.tasks =
            _stateNotImportantUrgent.value.tasks.sortedBy { it.startDate }
        _stateNotImportantNotUrgent.value.tasks =
            _stateNotImportantNotUrgent.value.tasks.sortedBy { it.startDate }
    }

    fun sortByDateLates() {
        _stateImportantUrgent.value.tasks =
            _stateImportantUrgent.value.tasks.sortedBy { it.startDate }.asReversed()
        _stateImportantNotUrgent.value.tasks =
            _stateImportantNotUrgent.value.tasks.sortedBy { it.startDate }.asReversed()
        _stateNotImportantUrgent.value.tasks =
            _stateNotImportantUrgent.value.tasks.sortedBy { it.startDate }.asReversed()
        _stateNotImportantNotUrgent.value.tasks =
            _stateNotImportantNotUrgent.value.tasks.sortedBy { it.startDate }.asReversed()
    }

    fun deleteAllCompliteTask() = viewModelScope.launch {
        deleteCompliteTasksUseCase()
    }

    fun deleteAllTask() = viewModelScope.launch {
        deleteAllTasksUseCase()
    }

    fun compliteTask(task: Task) {
        val iu =
            stateImportantUrgent.value.tasks.find { it.id == task.id && it.startTime == task.startTime }
        val inU =
            stateImportantNotUrgent.value.tasks.find { it.id == task.id && it.startTime == task.startTime }
        val nIu =
            stateNotImportantUrgent.value.tasks.find { it.id == task.id && it.startTime == task.startTime }
        val nInU =
            stateNotImportantNotUrgent.value.tasks.find { it.id == task.id && it.startTime == task.startTime }
        when {
            iu != null -> {
                val t = ImportantUrgentTask(
                    id = iu.id,
                    title = iu.title,
                    startDate = iu.startDate,
                    startTime = iu.startTime,
                    completionDate = iu.completionDate,
                    completionTime = iu.completionTime,
                    repeats = iu.repeats,
                    categoryId = iu.categoryId,
                    done = if (iu.done) 1 else 0
                )
                viewModelScope.launch {
                    compliteTaskUseCase.compliteImportantUrgentTask(t)
                }
            }
            inU != null -> {
                val t = ImportantNotUrgentTask(
                    id = inU.id,
                    title = inU.title,
                    startDate = inU.startDate,
                    startTime = inU.startTime,
                    completionDate = inU.completionDate,
                    completionTime = inU.completionTime,
                    repeats = inU.repeats,
                    categoryId = inU.categoryId,
                    done = if (inU.done) 1 else 0
                )
                viewModelScope.launch {
                    compliteTaskUseCase.compliteImportantNotUrgentTask(t)
                }
            }
            nIu != null -> {
                val t = NotImportantUrgentTask(
                    id = nIu.id,
                    title = nIu.title,
                    startDate = nIu.startDate,
                    startTime = nIu.startTime,
                    completionDate = nIu.completionDate,
                    completionTime = nIu.completionTime,
                    repeats = nIu.repeats,
                    categoryId = nIu.categoryId,
                    done = if (nIu.done) 1 else 0
                )
                viewModelScope.launch {
                    compliteTaskUseCase.compliteNotImportantUrgentTask(t)
                }
            }
            nInU != null -> {
                val t = NotImportantNotUrgentTask(
                    id = nInU.id,
                    title = nInU.title,
                    startDate = nInU.startDate,
                    startTime = nInU.startTime,
                    completionDate = nInU.completionDate,
                    completionTime = nInU.completionTime,
                    repeats = nInU.repeats,
                    categoryId = nInU.categoryId,
                    done = if (nInU.done) 1 else 0
                )
                viewModelScope.launch {
                    compliteTaskUseCase.compliteNotImportantNotUrgentTask(t)
                }
            }
        }
    }

    fun deleteTask(task: Task, color: Color) = viewModelScope.launch {
        when(color){
            Color(0xFF800B0B) ->{
                deleteTaskUseCase.deleteImportantUrgentTask(task)
            }
            Color(0xF027B112) ->{
                deleteTaskUseCase.deleteImportantNotUrgentTask(task)
            }
            Color(0xFFB6A403) ->{
                deleteTaskUseCase.deleteNotImportantUrgentTask(task)
            }
            Color(0xF1343D55) ->{
                deleteTaskUseCase.deleteNotImportantNotUrgentTask(task)
            }
        }
    }


}