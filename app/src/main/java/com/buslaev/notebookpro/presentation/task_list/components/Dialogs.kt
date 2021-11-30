package com.buslaev.notebookpro.presentation.task_list.components

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.buslaev.notebookpro.R
import com.buslaev.notebookpro.presentation.task_list.CategoriesViewModel
import com.vanpra.composematerialdialogs.*


@Composable
fun VisibilityDialog(
    dialogState: MaterialDialogState
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(
                text = stringResource(id = R.string.dialog_button_name_visibility_current_tasks),
                onClick = { },
                textStyle = TextStyle(color = Color.Black)
            )
            negativeButton(
                text = stringResource(id = R.string.dialog_button_name_visibility_all_tasks),
                onClick = {},
                textStyle = TextStyle(color = Color.Black)
            )
        },
        content = {}
    )
}

@Composable
fun FilterDialog(
    dialogState: MaterialDialogState,
    viewModel: CategoriesViewModel,
    returnSelectedItems: (MutableList<Int>) -> Unit,
) {
    val returnedItems = mutableListOf<Int>()
    var selectedItems by remember { mutableStateOf(setOf<Int>()) }
    val categoriesState = viewModel.stateImportantUrgent.collectAsState()
    val categoriesString = mutableListOf<String>()
    categoriesState.value.categories.forEach {
        categoriesString.add(it.categoryTitle)
    }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(
                text = stringResource(id = R.string.dialog_button_name_filter),
                onClick = {
                    selectedItems.forEach {
                        returnedItems.add(categoriesState.value.categories[it].categoryId!!)
                    }
                    returnSelectedItems(returnedItems)
                },
                textStyle = TextStyle(color = Color.Black)
            )
            negativeButton(
                text = stringResource(id = R.string.dialog_button_name_cancel),
                textStyle = TextStyle(color = Color.Black)
            )
        },
        content = {
            title(text = stringResource(id = R.string.dialog_title_filters))
            listItemsMultiChoice(
                list = categoriesString,
                onCheckedChange = {
                    selectedItems = it
                }
            )
        }
    )
}

@Composable
fun SortingDialog(
    dialogState: MaterialDialogState,
    returnSelectedSort: (Int) -> Unit,
) {
    var selectedSort by remember { mutableStateOf(0) }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(
                text = stringResource(id = R.string.dialog_button_name_sorting),
                onClick = { returnSelectedSort(selectedSort) },
                textStyle = TextStyle(color = Color.Black)
            )
        },
        content = {
            title(stringResource(id = R.string.dialog_title_sorted_by))
            listItemsSingleChoice(
                list = listOf("in ABC order", "date (the earliest)", "date (the latest)"),
                initialSelection = 0,
                onChoiceChange = {
                    selectedSort = it
                }
            )
        }
    )
}

@Composable
fun DeleteAllDialog(
    dialogState: MaterialDialogState,
    deleteCompletedTasks: () -> Unit,
    deleteAllTasks: () -> Unit,
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(
                text = stringResource(id = R.string.dialog_button_name_delete_all),
                onClick = { deleteAllTasks() },
                textStyle = TextStyle(color = Color.Black)
            )
            negativeButton(
                text = stringResource(id = R.string.dialog_button_name_delete_completed),
                onClick = { deleteCompletedTasks() },
                textStyle = TextStyle(color = Color.Black)
            )
        },
        content = {
            title(stringResource(id = R.string.dialog_title_delete_all_tasks))
        }
    )
}