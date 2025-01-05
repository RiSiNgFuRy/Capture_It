package com.example.commonuicomponents.commonWidgets

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.commonuicomponents.helpers.ScrollType
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withTimeout

@Composable
fun CLazyRow(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal =
        if (!reverseLayout) Arrangement.Start else Arrangement.End,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    scrollType: ScrollType = ScrollType.Normal,
    indexOfItemInHighlight: MutableState<Int>? = null,
    content: LazyListScope.() -> Unit,
) {
    LazyRow(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        content = content,
    )
    if (scrollType != ScrollType.Normal)
        setScrollType(scrollType, state, indexOfItemInHighlight)
}

@Composable
fun setScrollType (
    scrollType: ScrollType,
    lazyListState: LazyListState,
    indexOfItemInHighlight: MutableState<Int>? = null
) {
    val currentIndex = indexOfItemInHighlight ?: remember { mutableIntStateOf(0) }
    val previousFirstIndex = remember { mutableIntStateOf(0) }
    var canReplay = true

    LaunchedEffect(lazyListState.isScrollInProgress) {
        currentIndex.value = lazyListState.firstVisibleItemIndex
        val firstVisibleItemOffset = lazyListState.firstVisibleItemScrollOffset

        if (lazyListState.canScrollForward.not()) {
            indexOfItemInHighlight?.value = currentIndex.value + 1
        } else if (firstVisibleItemOffset == 0) {
            indexOfItemInHighlight?.value = currentIndex.value
        }

        when(scrollType) {
            is ScrollType.SnapBackwards -> {
                if (lazyListState.isScrollInProgress.not() &&
                    firstVisibleItemOffset > 0 &&
                    lazyListState.canScrollForward
                ) {
                    indexOfItemInHighlight?.value = currentIndex.value
                    lazyListState.animateScrollToItem(currentIndex.value)
                }
            }

            is ScrollType.SnapForward -> {
                if (lazyListState.isScrollInProgress.not() &&
                    firstVisibleItemOffset > 0 &&
                    lazyListState.canScrollForward
                ) {
                    lazyListState.animateScrollToItem(currentIndex.value + 1)
                }
            }

            is ScrollType.SnapInScrolledDirection -> {
                if (lazyListState.canScrollForward.not()) {
                    previousFirstIndex.value = currentIndex.value + 1
                } else if (firstVisibleItemOffset == 0) {
                    previousFirstIndex.value = currentIndex.value
                }

                if (lazyListState.isScrollInProgress.not() &&
                    firstVisibleItemOffset > 0 &&
                    lazyListState.canScrollForward
                ) {
                    val snapBy = if (previousFirstIndex.value == currentIndex.value) 1 else 0
                    lazyListState.animateScrollToItem(currentIndex.value + snapBy)
                }
            }

            is ScrollType.AutoScrollLeft -> {
                if (lazyListState.canScrollForward.not()) {
                    previousFirstIndex.value = currentIndex.value + 1
                } else if (firstVisibleItemOffset == 0) {
                    previousFirstIndex.value = currentIndex.value
                }

                withTimeout(scrollType.delayTimeInMillis) {
                    if (isActive && lazyListState.isScrollInProgress.not()) {
                        lazyListState.animateScrollToItem(lazyListState.layoutInfo.totalItemsCount - 1
                                - (currentIndex.value % lazyListState.layoutInfo.totalItemsCount))
                    }
                }
            }

            is ScrollType.AutoScrollRight -> {
                if (lazyListState.isScrollInProgress.not()) {
                    canReplay = true
                    lazyListState.animateScrollToItem(
                        (currentIndex.value + 1) % lazyListState.layoutInfo.totalItemsCount
                    )
                }
            }

            else -> {}
        }
    }
}