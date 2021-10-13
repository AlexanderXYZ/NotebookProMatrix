package com.buslaev.notebookpro.presentation.add_task.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable

@Composable
fun FabBackToList(
    onClick: () -> Unit
) {
    val iconDone = Icons.Filled.Done
    FloatingActionButton(onClick = { onClick() }) {
        Icon(imageVector = iconDone, contentDescription = "Confirm task")
    }
}