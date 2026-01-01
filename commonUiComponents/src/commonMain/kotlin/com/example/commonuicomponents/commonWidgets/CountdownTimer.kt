package com.example.commonuicomponents.commonWidgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.example.commonuicomponents.helpers.TimeFormat
import kotlinx.coroutines.delay

@Composable
fun CountdownTimer(
    initialTime: Int,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    format: TimeFormat = TimeFormat.HH_MM_SS,
    onTick: (secondsLeft: Int) -> Unit = {}
) {
    var timeLeft by remember { mutableStateOf(initialTime) }

    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
            onTick(timeLeft)
        }
    }

    val formattedTime = when (format) {
        TimeFormat.HH_MM_SS -> {
            val hours = timeLeft / 3600
            val minutes = (timeLeft % 3600) / 60
            val seconds = timeLeft % 60
            "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
        }
        TimeFormat.MM_SS -> {
            val minutes = timeLeft / 60
            val seconds = timeLeft % 60
            "${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
        }

        TimeFormat.SS -> timeLeft.toString().padStart(2, '0')
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = formattedTime,
            style = textStyle
        )
    }
}