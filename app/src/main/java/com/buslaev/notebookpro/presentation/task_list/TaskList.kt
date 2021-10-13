package com.buslaev.notebookpro.presentation.task_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.buslaev.notebookpro.presentation.add_task.components.ContentAddTask
import com.buslaev.notebookpro.presentation.task_list.components.*
import com.buslaev.notebookpro.presentation.theme.colorFabExpended
import com.google.accompanist.pager.ExperimentalPagerApi
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun TaskList(
    navController: NavController,
) {
    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val dialogVisibilityState = rememberMaterialDialogState()
    val dialogFilterState = rememberMaterialDialogState()
    val dialogSortState = rememberMaterialDialogState()
    val dialogDeleteAllState = rememberMaterialDialogState()

    var color by remember { mutableStateOf(Color.Red) }
    var tasks by remember { mutableStateOf(Tasks.IMPORTANT_URGENT) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            ContentAddTask(
                color = color,
                task = tasks,
                closeSheet = {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                }
            )
        },
        sheetShape = MaterialTheme.shapes.medium,
    )
    {
        Scaffold(
            topBar = {
                TaskListTopAppBar(
                    scope = coroutineScope,
                    scaffoldState = scaffoldState
                )
            },
            drawerContent = {
                TaskListDrawerContent()
            },
            floatingActionButton = {
                MultiFabAddTask(
                    toState = toState,
                    stateChanged = { state ->
                        toState = state
                    },
                    onFabItemClicked = {
                        color = it.color
                        tasks = it.identifier
                        toState = MultiFabState.COLLAPSED
                        coroutineScope.launch {
                            if (bottomSheetState.isVisible) {
                                bottomSheetState.hide()
                            } else {
                                bottomSheetState.show()
                            }
                        }
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            content = {
                MainContent(navController = navController)
                if (toState == MultiFabState.EXPANDED) {
                    GrayBox(onClick = { toState = MultiFabState.COLLAPSED })
                }
            },
            bottomBar = {
                BottomBarListTasks(
                    onSeeClick = {
                        dialogVisibilityState.show()
                    },
                    onFilterClick = {
                        dialogFilterState.show()
                    },
                    onSortClick = {
                        dialogSortState.show()
                    },
                    onDeleteAllClick = {
                        dialogDeleteAllState.show()
                    }
                )
            }
        )
    }
    VisibilityDialog(
        dialogState = dialogVisibilityState
    )
    FilterDialog(
        dialogState = dialogFilterState,
        returnSelectedItems = {

        }
    )
    SortingDialog(
        dialogState = dialogSortState,
        returnSelectedSort = {

        }
    )
    DeleteAllDialog(
        dialogState = dialogDeleteAllState,
        deleteCompletedTasks = {

        },
        deleteAllTasks = {

        }
    )

}

@Composable
fun GrayBox(
    onClick: () -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(colorFabExpended)
        .clickable { onClick() }
    )
}

enum class Tasks {
    IMPORTANT_URGENT,
    IMPORTANT_NOT_URGENT,
    NOT_IMPORTANT_URGENT,
    NOT_IMPORTANT_NOT_URGENT
}