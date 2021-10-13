package com.buslaev.notebookpro.presentation.task_list.components

import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.buslaev.notebookpro.common.Constants.IMPORTANT_NOT_URGENT
import com.buslaev.notebookpro.common.Constants.IMPORTANT_URGENT
import com.buslaev.notebookpro.common.Constants.NOT_IMPORTANT_NOT_URGENT
import com.buslaev.notebookpro.common.Constants.NOT_IMPORTANT_URGENT
import com.buslaev.notebookpro.presentation.task_list.Tasks
import com.buslaev.notebookpro.presentation.theme.colorImportantNotUrgent
import com.buslaev.notebookpro.presentation.theme.colorImportantUrgent
import com.buslaev.notebookpro.presentation.theme.colorNotImportantNotUrgent
import com.buslaev.notebookpro.presentation.theme.colorNotImportantUrgent

@Composable
fun MultiFabAddTask(
    toState: MultiFabState,
    stateChanged: (fabState: MultiFabState) -> Unit,
    onFabItemClicked: (item: MultiFabItem) -> Unit,
) {
    val iconAdd = Icons.Filled.Add
    val transition = updateTransition(targetState = toState, label = "")
    val rotation by transition.animateFloat(label = "") { state ->
        if (state == MultiFabState.EXPANDED) 225F else 0F
    }

    val items = listOf(
        MultiFabItem(Tasks.IMPORTANT_URGENT,
            "",
            color = colorImportantUrgent,
            x = -(80).dp,
            y = 0.dp),
        MultiFabItem(Tasks.IMPORTANT_NOT_URGENT,
            "",
            color = colorImportantNotUrgent,
            x = -(35).dp,
            y = -(45).dp),
        MultiFabItem(Tasks.NOT_IMPORTANT_URGENT,
            "",
            color = colorNotImportantUrgent,
            x = 35.dp,
            y = -(45).dp),
        MultiFabItem(Tasks.NOT_IMPORTANT_NOT_URGENT,
            "",
            color = colorNotImportantNotUrgent,
            x = 80.dp,
            y = 0.dp)
    )

    val scale: Float by transition.animateFloat(label = "") { state ->
        if (state == MultiFabState.EXPANDED) 64f else 0f
    }
    val alpha: Float by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 50)
        },
        label = ""
    ) { state ->
        if (state == MultiFabState.EXPANDED) 1f else 0f
    }
    Box(contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.offset(y = -(32).dp)) {
            items.forEach { item ->
                Box(modifier = Modifier
                    .offset(x = item.x, y = item.y)
                    .size(32.dp)
                    .clip(CircleShape)
                ) {
                    MiniFloatingActionButton(
                        item = item,
                        buttonScale = scale,
                        iconAlpha = alpha,
                        onActionClicked = {
                            onFabItemClicked(it)
                        }
                    )
                }
            }
        }
        FloatingActionButton(onClick = {
            stateChanged(
                if (transition.currentState == MultiFabState.EXPANDED) {
                    MultiFabState.COLLAPSED
                } else MultiFabState.EXPANDED
            )
        }) {
            Icon(
                imageVector = iconAdd,
                contentDescription = "Add task",
                modifier = Modifier.rotate(rotation)
            )
        }
    }

}

@Composable
fun MiniFloatingActionButton(
    item: MultiFabItem,
    buttonScale: Float,
    iconAlpha: Float,
    onActionClicked: (item: MultiFabItem) -> Unit,
) {
    val buttonColor = MaterialTheme.colors.secondary

    Canvas(modifier = Modifier
        .size(32.dp)
        .clickable(onClick = {
            onActionClicked(item)
            Log.d("dawdadw", "canvas click ${item.identifier}")
        })
    ) {
        drawCircle(
            color = buttonColor,
            radius = buttonScale
        )
//        drawImage(
//            item.icon,
//            topLeft = Offset(
//                (this.center.x) - (item.icon.width / 2),
//                (this.center.y) - (item.icon.width / 2)
//            ),
//        alpha=iconAlpha
//        )
    }


}

class MultiFabItem(
    val identifier: Tasks,
    //val icon: ImageBitmap,
    val label: String,
    val color: Color,
    val x: Dp,
    val y: Dp,
)

enum class MultiFabState {
    COLLAPSED, EXPANDED
}