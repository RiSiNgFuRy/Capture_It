package com.example.commonuicomponents.commonWidgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun CTextButton(
    text: String,
    onClick: () -> Unit,
    type: TextButtonType = TextButtonType.SECONDARY,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    interactionSource: MutableInteractionSource? = null,
) {
    TextButton(
        modifier = modifier
            .background(
                color = getBtnColor(type),
                shape = getBtnShape(type)
            ),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource
    ) {
        Text(
            text = text,
            style = getTextStyle(type),
            color = getTextColor(type),
        )
    }
}

@Composable
private fun getBtnColor(type: TextButtonType) = when(type) {
    TextButtonType.SECONDARY -> MaterialTheme.colorScheme.onSecondary
}

@Composable
private fun getBtnShape(type: TextButtonType) = when(type) {
    TextButtonType.SECONDARY -> MaterialTheme.shapes.large
}

@Composable
private fun getTextStyle(type: TextButtonType) = when(type) {
    TextButtonType.SECONDARY -> MaterialTheme.typography.titleMedium
}

@Composable
private fun getTextColor(type: TextButtonType) = when(type) {
    TextButtonType.SECONDARY -> MaterialTheme.colorScheme.surfaceContainerLowest
}

enum class TextButtonType {
    SECONDARY
}