package com.buslaev.notebookpro.presentation.add_task.components

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker

@Composable
fun DatePicker(
    dialogState: MaterialDialogState,
    color: Color,
    returnDate: (String) -> Unit,
) {
    var dateString by remember { mutableStateOf("") }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(text = "Ok", onClick = { returnDate(dateString) })
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = color,
                activeTextColor = color
            )
        ) { date ->
            dateString = date.toString()
        }
    }
}

@Composable
fun TimePicker(
    dialogState: MaterialDialogState,
    color: Color,
    returnTime: (String) -> Unit,
) {
    var timeString by remember { mutableStateOf("") }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(text = "Ok", onClick = { returnTime(timeString) })
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            colors = TimePickerDefaults.colors(
                activeBackgroundColor = color.copy(0.3f),
                selectorColor = color
            )
        ) { time ->
            timeString = time.toString()
        }
    }
}