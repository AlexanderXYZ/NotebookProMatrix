package com.buslaev.notebookpro.presentation.task_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DriveFileMove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buslaev.notebookpro.domain.model.Task
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

@ExperimentalMaterialApi
@Composable
fun TaskItem(
    task: Task,
    onCheckedClick: () -> Unit,
    onClick: () -> Unit,
) {
    var checkedState by remember { mutableStateOf(task.done) }

    RevealSwipe(
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
        directions = setOf(RevealDirection.StartToEnd),
        hiddenContentStart = {
            ActionRow(
                onDone = { },
                onMove = { },
                onDelete = { }
            )
        },
        content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = 8.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(8.dp))

                    Checkbox(
                        checked = checkedState,
                        onCheckedChange = {
                            checkedState = !checkedState
                            onCheckedClick()
                        }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(text = task.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        if (task.completionDate.isNotEmpty()) {
                            Text(
                                text = "${task.completionDate}, ${task.completionTime}",
                                fontSize = 12.sp
                            )
                        }
                        if (task.categoryId != null) {
                            Text(text = task.categoryTitle, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ActionRow(
    onDone: () -> Unit,
    onMove: () -> Unit,
    onDelete: () -> Unit,
) {
    val iconDone = Icons.Filled.Done
    val iconMove = Icons.Filled.DriveFileMove
    val iconDelete = Icons.Filled.DeleteOutline
    Row() {
        Icon(imageVector = iconDone, contentDescription = "Done")
        Icon(imageVector = iconMove, contentDescription = "Move")
        Icon(imageVector = iconDelete, contentDescription = "Delete")
    }
}