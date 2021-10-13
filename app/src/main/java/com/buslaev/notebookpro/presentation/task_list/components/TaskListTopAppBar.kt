package com.buslaev.notebookpro.presentation.task_list.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TaskListTopAppBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { scope.launch { scaffoldState.drawerState.open() } },
                content = { Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu") })

        },
        title = {
            Text(text = "Notebook Pro")
        }
    )
}