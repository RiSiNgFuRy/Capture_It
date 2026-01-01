package com.example.commonuicomponents.carousels

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.commonuicomponents.FeatureTile
import com.example.commonuicomponents.helpers.IndicatorType

@Composable
fun IndicatorWidgetProvider(type: IndicatorType, isSelected: Boolean) {
    return when(type) {
        is IndicatorType.FeatureTileIndicator -> {
            FeatureTile(
                type.modifier,
                type.imageSize,
                type.imagePath,
                type.title,
                type.titleTextStyle,
                isSelected
            )
        }

        else -> { Box(modifier = Modifier) }
    }
}