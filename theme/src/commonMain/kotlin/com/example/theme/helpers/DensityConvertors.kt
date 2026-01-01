package com.example.theme.helpers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Stable
val Int.txtDp: TextUnit
    @Composable
    get() = textDp()

@Composable
private fun Int.textDp() = with(LocalDensity.current) { this@textDp.dp.toSp() }


@Stable
val Double.textDp: TextUnit
    @Composable
    get() = textDp()

@Composable
private fun Double.textDp() = with(LocalDensity.current) { this@textDp.dp.toSp() }

@Stable
val Int.toDp: Dp
    @Composable
    get() = toDp()

@Composable
private fun Int.toDp() = (this / LocalDensity.current.density).dp
