package com.buslaev.notebookpro.common

import android.os.Build
import java.time.LocalTime

internal fun LocalTime.noSeconds(): LocalTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    LocalTime.of(this.hour, this.minute)
} else {
    TODO("VERSION.SDK_INT < O")
}