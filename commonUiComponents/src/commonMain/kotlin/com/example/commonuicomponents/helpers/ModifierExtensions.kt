package com.example.commonuicomponents.helpers

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.visibility(visible: Boolean): Modifier = this.then(
    if (visible) this else this.size(0.dp)
)