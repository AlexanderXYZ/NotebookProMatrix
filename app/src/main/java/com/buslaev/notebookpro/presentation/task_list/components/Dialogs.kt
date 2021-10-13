package com.buslaev.notebookpro.presentation.task_list.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.buslaev.notebookpro.R
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
                onClick = { }
            )
            negativeButton(
                text = stringResource(id = R.string.dialog_button_name_visibility_all_tasks),
                onClick = {}
            )
        },
        content = {}
    )
}

@Composable
fun FilterDialog(
    dialogState: MaterialDialogState,
    returnSelectedItems: (Set<Int>) -> Unit,
) {
    var selectedItems by remember { mutableStateOf(setOf<Int>()) }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(
                text = stringResource(id = R.string.dialog_button_name_filter),
                onClick = { returnSelectedItems(selectedItems) }
            )
            negativeButton(
                text = stringResource(id = R.string.dialog_button_name_cancel)
            )
        },
        content = {
            title(text = stringResource(id = R.string.dialog_title_filters))
            listItemsMultiChoice(
                list = listOf("1", "2", "3"),
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
                onClick = { returnSelectedSort(selectedSort) })
        },
        content = {
            title(stringResource(id = R.string.dialog_title_sorted_by))
            listItemsSingleChoice(
                list = listOf("daw", "daw,", "dwadw"),
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
                onClick = { deleteAllTasks() })
            negativeButton(
                text = stringResource(id = R.string.dialog_button_name_delete_completed),
                onClick = { deleteCompletedTasks() })
        },
        content = {
            title(stringResource(id = R.string.dialog_title_delete_all_tasks))
        }
    )
}