package com.buslaev.notebookpro.presentation.add_task.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.buslaev.notebookpro.presentation.add_task.AddTaskViewModel
import com.buslaev.notebookpro.presentation.task_list.Tasks
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun ContentAddTask(
    viewModel: AddTaskViewModel = hiltViewModel(),
    color: Color,
    task: Tasks,
    closeSheet: () -> Unit,
) {
    var titleTask by remember { mutableStateOf("") }
    var completionDate by remember { mutableStateOf("") }
    var completionTime by remember { mutableStateOf("") }
    var repeat by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf(0) }

    val dataDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    var titleError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(6.dp)
    ) {

        TitleTextField(
            onValueChange = { text, error ->
                titleTask = text
                titleError = error
            },
            color = color,
            titleError = titleError
        )

        SmallSpacerHeight()

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextFieldAndDropDownMenu(
                viewModel = viewModel,
                color = color,
                onChoosed = {
                    categoryId = it
                })
            Icon(
                imageVector = Icons.Filled.PostAdd,
                contentDescription = "Add list of tasks",
                modifier = Modifier.clickable {

                })
        }

        SmallSpacerHeight()

        RowItem(
            value = completionDate,
            onClick = {
                dataDialogState.show()
            },
            icon = Icons.Filled.Event,
            contentDescription = "Date",
            color = color,
            labelText = "Date"
        )

        SmallSpacerHeight()

        RowItem(
            value = completionTime,
            onClick = {
                timeDialogState.show()
            },
            icon = Icons.Filled.MoreTime,
            contentDescription = "Time",
            color = color,
            labelText = "Time"
        )

        SmallSpacerHeight()

        RowItem(
            value = repeat,
            onClick = { },
            icon = Icons.Filled.Repeat,
            contentDescription = "Repeat",
            color = color,
            labelText = "Repeat"
        )

        SmallSpacerHeight()

        ActionRow(
            onPositiveButtonClick = {
                titleError = titleTask.isEmpty()
                if (!titleError) {
                    viewModel.insertTask(
                        title = titleTask,
                        completionDate = completionDate,
                        completionTime = completionTime,
                        repeats = repeat,
                        categoryId = categoryId,
                        tasks = task
                    )
                    closeSheet()
                }
            },
            onNegativeButtonClick = {
                closeSheet()
            },
            color = color
        )
    }
    DatePicker(
        dialogState = dataDialogState,
        color = color,
        returnDate = { dateString ->
            completionDate = dateString
        }
    )
    TimePicker(
        dialogState = timeDialogState,
        color = color,
        returnTime = { timeString ->
            completionTime = timeString
        }
    )
}

@Composable
fun TitleTextField(
    color: Color,
    onValueChange: (String, Boolean) -> Unit,
    titleError: Boolean
) {
    var value by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = {
                value = it
                onValueChange(it, it.isEmpty())
            },
            label = { Text(text = "Task") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = color,
                focusedIndicatorColor = color,
                focusedLabelColor = color,
                backgroundColor = Color.White
            ),
            isError = titleError
        )
        if (titleError) {
            Text(
                text = "Title doesn`t be empty",
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RowItem(
    value: String,
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    color: Color,
    labelText: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = color,
                focusedIndicatorColor = color,
                focusedLabelColor = color,
                backgroundColor = Color.White,
                textColor = Color.Black
            ),
            modifier = Modifier
                .clickable {
                    onClick()
                },
            label = { Text(text = labelText) },

            )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.clickable {
                onClick()
            },
            tint = color
        )
    }
}

@Composable
fun ActionRow(
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: () -> Unit,
    color: Color,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextAction(text = "CANCEL", color = color, click = { onNegativeButtonClick() })
        TextAction(text = "ADD", color = color, click = { onPositiveButtonClick() })
    }
}

@Composable
fun TextAction(
    text: String,
    color: Color,
    click: () -> Unit,
) {
    Text(
        text = text,
        color = color,
        modifier = Modifier.clickable { click() },
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.width(16.dp))
}

@Composable
fun OutlinedTextFieldAndDropDownMenu(
    viewModel: AddTaskViewModel,
    color: Color,
    onChoosed: (Int) -> Unit,
) {
    var expended by remember { mutableStateOf(false) }
    val categoryState by viewModel.categoryState.collectAsState()
    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expended) {
        Icons.Filled.ArrowDropUp
    } else Icons.Filled.ArrowDropDown

    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {
                selectedText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    textFieldSize = it.size.toSize()
                },
            label = { Text(text = "Category") },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "categories",
                    modifier = Modifier.clickable { expended = !expended })
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = color,
                focusedIndicatorColor = color,
                focusedLabelColor = color,
                backgroundColor = Color.White
            ),
            readOnly = true
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            categoryState.categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = category.categoryTitle
                        expended = false
                        category.categoryId?.let { onChoosed(category.categoryId!!) }
                    }
                ) {
                    Text(text = category.categoryTitle)
                }
            }
        }
    }
}

@Composable
fun SmallSpacerHeight() {
    Spacer(modifier = Modifier.height(12.dp))
}