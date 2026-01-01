package com.example.commonuicomponents.commonWidgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieClipSpec
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition

@Composable
fun CLottieAnimation(
    compositionSpec: LottieCompositionSpec,
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
    restartOnPlay: Boolean = true,
    clipSpec: LottieClipSpec? = null,
    speed: Float = 1f,
    iterations: Int = 1,
    reverseOnRepeat: Boolean = false,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    clipToCompositionBounds: Boolean = true,
) {
    val composition by rememberLottieComposition(spec = compositionSpec)

    LottieAnimation(
        composition = composition,
        modifier = modifier,
        isPlaying = isPlaying,
        restartOnPlay = restartOnPlay,
        clipSpec = clipSpec,
        speed = speed,
        iterations = iterations,
        reverseOnRepeat = reverseOnRepeat,
        alignment = alignment,
        contentScale = contentScale,
        clipToCompositionBounds = clipToCompositionBounds,
    )
}
