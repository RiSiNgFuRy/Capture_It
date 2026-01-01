package com.example.commonuicomponents.helpers

import androidx.annotation.ColorInt
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

sealed class IndicatorType {

    data class DotIndicator(
        val modifier: Modifier,
        @ColorInt val color: Int
    ): IndicatorType()

    data class FeatureTileIndicator(
        val modifier: Modifier,
        val imageSize: Dp,
        val imagePath: String,
        val title: String,
        val titleTextStyle: TextStyle,
    ): IndicatorType()
}