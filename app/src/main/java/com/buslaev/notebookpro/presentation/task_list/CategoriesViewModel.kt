package com.buslaev.notebookpro.presentation.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buslaev.notebookpro.domain.use_cases.category.GetCategoriesUseCase
import com.buslaev.notebookpro.presentation.add_task.CategoriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _stateImportantUrgent = MutableStateFlow(CategoriesState())
    val stateImportantUrgent: StateFlow<CategoriesState> = _stateImportantUrgent

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { list ->
                _stateImportantUrgent.value = CategoriesState(categories = list, empty = false)
            }
        }
    }
}