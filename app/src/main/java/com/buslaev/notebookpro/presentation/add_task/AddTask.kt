package com.buslaev.notebookpro.presentation.add_task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.buslaev.notebookpro.common.Screen
import com.buslaev.notebookpro.presentation.add_task.components.FabBackToList
import com.buslaev.notebookpro.presentation.add_task.components.ContentAddTask

@Composable
fun AddTask(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back to Task List",
                        modifier = Modifier
                            .clickable { navController.navigate(Screen.TaskList.route) }
                            .padding(start = 12.dp))
                },
                title = { Text(text = "New task") },
            )
        },
        floatingActionButton = {
            FabBackToList {
                navController.navigate(Screen.TaskList.route)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            //ContentAddTask()
        }
    )
}














