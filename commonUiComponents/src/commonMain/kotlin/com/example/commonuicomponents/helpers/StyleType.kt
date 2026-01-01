package com.example.commonuicomponents.helpers

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

enum class StyleType {
    SECONDARY
}

@Composable
fun getBtnColor(type: StyleType) = when(type) {
    StyleType.SECONDARY -> MaterialTheme.colorScheme.onSecondary
}

@Composable
fun getBtnShape(type: StyleType) = when(type) {
    StyleType.SECONDARY -> MaterialTheme.shapes.large
}

@Composable
fun getTextStyle(type: StyleType) = when(type) {
    StyleType.SECONDARY -> MaterialTheme.typography.titleMedium
}

@Composable
fun getTextColor(type: StyleType) = when(type) {
    StyleType.SECONDARY -> MaterialTheme.colorScheme.surfaceContainerLowest
}