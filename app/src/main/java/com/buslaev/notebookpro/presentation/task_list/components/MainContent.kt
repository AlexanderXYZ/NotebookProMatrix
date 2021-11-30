package com.buslaev.notebookpro.presentation.task_list.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DriveFileMove
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.FrameMetricsAggregator.ANIMATION_DURATION
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.buslaev.notebookpro.domain.model.Task
import com.buslaev.notebookpro.presentation.task_list.TaskListState
import com.buslaev.notebookpro.presentation.task_list.TaskListViewModel
import com.buslaev.notebookpro.presentation.theme.colorImportantNotUrgent
import com.buslaev.notebookpro.presentation.theme.colorImportantUrgent
import com.buslaev.notebookpro.presentation.theme.colorNotImportantNotUrgent
import com.buslaev.notebookpro.presentation.theme.colorNotImportantUrgent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun MainContent(
    navController: NavController,
    viewModel: TaskListViewModel = hiltViewModel(),
) {
    val pagerState = rememberPagerState(pageCount = 2)

    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> {
                FirstPage(viewModel = viewModel)
            }
            1 -> {
                SecondPage(viewModel = viewModel)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun FirstPage(
    viewModel: TaskListViewModel
) {
    val importantUrgent by viewModel.stateImportantUrgent.collectAsState()
    val notImportantUrgent by viewModel.stateNotImportantUrgent.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
            .padding(bottom = 48.dp)
    ) {

        BoxMatrix(
            modifier = Modifier
                .fillMaxSize()
                .weight(1F),
            color = colorImportantUrgent
        ) {
            ColumnContent(
                title = "Important Urgent",
                color = colorImportantUrgent,
                taskState = importantUrgent,
                viewModel = viewModel
            )
        }

        BoxMatrix(
            modifier = Modifier
                .fillMaxSize()
                .weight(1F),
            //.graphicsLayer(shape = TicketShapeBottomRight(24F), clip = true),
            color = colorNotImportantUrgent
        ) {
            ColumnContent(
                title = "Not Important Urgent",
                color = colorNotImportantUrgent,
                taskState = notImportantUrgent,
                viewModel = viewModel
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SecondPage(
    viewModel: TaskListViewModel
) {

    val importantNotUrgent by viewModel.stateImportantNotUrgent.collectAsState()
    val notImportantNotUrgent by viewModel.stateNotImportantNotUrgent.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
            .padding(bottom = 48.dp)
    ) {

        BoxMatrix(
            modifier = Modifier
                .fillMaxSize()
                .weight(1F),
            color = colorImportantNotUrgent
        ) {
            ColumnContent(
                title = "Important Not Urgent",
                color = colorImportantNotUrgent,
                taskState = importantNotUrgent,
                viewModel = viewModel
            )
        }

        BoxMatrix(
            modifier = Modifier
                .fillMaxSize()
                .weight(1F),
            //.graphicsLayer(shape = TicketShapeBottomLeft(24F), clip = true),
            color = colorNotImportantNotUrgent
        ) {
            ColumnContent(
                title = "Not Important Not Urgent",
                color = colorNotImportantNotUrgent,
                taskState = notImportantNotUrgent,
                viewModel = viewModel
            )
        }

    }
}

@Composable
fun BoxMatrix(
    modifier: Modifier = Modifier,
    color: Color,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(6.dp)
            .border(
                width = 1.dp,
                color = color,
                shape = RoundedCornerShape(24F)
            )
    ) {
        content()
    }
}

@ExperimentalMaterialApi
@Composable
fun ColumnContent(
    title: String,
    color: Color,
    taskState: TaskListState,
    viewModel: TaskListViewModel
) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = color
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color)
        )
        LazyColumn {
            items(taskState.tasks) { task ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    TaskItem(
                        task = task,
                        onCheckedClick = {
                            viewModel.compliteTask(it)
                        },
                        onDone = {
                            viewModel.compliteTask(it)
                        },
                        onDelete = {
                            viewModel.deleteTask(it, color)
                        }
                    )
                }
            }
        }
        if (taskState.tasks.isEmpty()) {
            Text(
                text = "Empty tasks",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
    }
}