package com.buslaev.notebookpro.common

sealed class Screen(val route: String) {
    object TaskList : Screen("task_list")
    object AddTask : Screen("add_list")
    object DetailTask : Screen("detail_list")
}
