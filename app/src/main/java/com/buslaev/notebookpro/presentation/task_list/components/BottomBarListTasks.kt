package com.buslaev.notebookpro.presentation.task_list.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomBarListTasks(
    onSeeClick: () -> Unit,
    onFilterClick: () -> Unit,
    onSortClick: () -> Unit,
    onDeleteAllClick: () -> Unit,
) {
    BottomAppBar(
        cutoutShape = CircleShape
    ) {
        BottomBarItem(
            modifier = Modifier.weight(1F),
            icon = Icons.Filled.Visibility,
            contentDescription = "Visibility",
            onClick = { onSeeClick() })

        BottomBarItem(
            modifier = Modifier.weight(1F),
            icon = Icons.Filled.Filter,
            contentDescription = "Filter",
            onClick = { onFilterClick() })

        Spacer(modifier = Modifier.weight(1F))

        BottomBarItem(
            modifier = Modifier.weight(1F),
            icon = Icons.Filled.Sort,
            contentDescription = "Sort",
            onClick = { onSortClick() })

        BottomBarItem(
            modifier = Modifier.weight(1F),
            icon = Icons.Filled.DeleteSweep,
            contentDescription = "Delete",
            onClick = { onDeleteAllClick() })
    }
}

@Composable
fun BottomBarItem(
    modifier: Modifier,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.Black
        )
    }
}