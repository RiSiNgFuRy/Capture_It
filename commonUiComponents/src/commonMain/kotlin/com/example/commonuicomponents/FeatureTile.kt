package com.example.commonuicomponents

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.commonuicomponents.commonWidgets.CustomImage

@Composable
fun FeatureTile(
    modifier: Modifier = Modifier,
    imageSize: Dp = 1.dp,
    imagePath: String,
    title: String,
    isSelected: Boolean = false
) {
    val imgSize = animateDpAsState(
        targetValue = if (isSelected)
            imageSize.times(1.5f)
        else imageSize,
        animationSpec = tween(500)
    )

    Column(
        modifier = modifier
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomImage(
            path = imagePath,
            contentDescription = title,
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    shape = CircleShape
                )
                .size(imgSize.value)
                .wrapContentSize()
                .clipToBounds()
        )

        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
            textAlign = TextAlign.Center,
            softWrap = true,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}