package com.example.commonuicomponents.commonWidgets

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.commonuicomponents.helpers.StyleType
import com.example.commonuicomponents.helpers.getBtnColor
import com.example.commonuicomponents.helpers.getBtnShape
import com.example.commonuicomponents.helpers.getTextColor
import com.example.commonuicomponents.helpers.getTextStyle

@Composable
fun CTextButton(
    text: String,
    onClick: () -> Unit,
    type: StyleType = StyleType.SECONDARY,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val effectiveInteractionSource = remember { MutableInteractionSource() }
    val isPressed by effectiveInteractionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
    )

    Box(
        modifier = modifier
            .scale(scale)
            .background(
                color = getBtnColor(type),
                shape = getBtnShape(type)
            )
            .clickable(
                interactionSource = effectiveInteractionSource,
                indication = null,
                enabled = enabled,
            ) { onClick.invoke() },
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Center),
            text = text,
            style = getTextStyle(type),
            color = getTextColor(type),
        )
    }
}
