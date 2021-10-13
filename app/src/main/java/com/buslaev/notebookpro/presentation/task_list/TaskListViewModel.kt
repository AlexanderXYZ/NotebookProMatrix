package com.buslaev.notebookpro.presentation.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buslaev.notebookpro.data.locale.model.toTask
import com.buslaev.notebookpro.domain.use_cases.task.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
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


}