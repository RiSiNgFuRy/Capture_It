package com.example.commonuicomponents.carousels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.commonuicomponents.commonWidgets.CLazyRow
import com.example.commonuicomponents.helpers.Direction
import com.example.commonuicomponents.helpers.ScrollType


@Composable
fun CarouselView(
    modifier: Modifier = Modifier,
    items: List<@Composable () -> Unit>,
    indexOfItemInHighlight: MutableState<Int>? = null,
    indicatorWidget: List<@Composable ((isSelected: Boolean) -> Unit)>? = null,
    indicatorDirection: Direction = Direction.BOTTOM,
    scrollType: ScrollType = ScrollType.Normal,
    indicatorArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center,
    indicatorPadding: PaddingValues = PaddingValues(16.dp)
) {
    val currentIndex = indexOfItemInHighlight ?: remember { mutableIntStateOf(0) }
    val lazyListState = rememberLazyListState()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (itemView, indicators) = createRefs()

        CLazyRow(
            modifier = Modifier
                .constrainAs(itemView) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            state = lazyListState,
            scrollType = scrollType,
            indexOfItemInHighlight = currentIndex
        ) {
            items(items.size) {
                items[it].invoke()
            }
        }

        indicatorWidget?.run {
            when(indicatorDirection) {
                Direction.BOTTOM,
                Direction.TOP -> Row(
                    modifier = Modifier
                        .constrainAs(indicators) {
                            if (indicatorDirection == Direction.BOTTOM) {
                                bottom.linkTo(itemView.bottom)
                            } else {
                                top.linkTo(itemView.top)
                            }
                            start.linkTo(itemView.start)
                            end.linkTo(itemView.end)
                        }
                        .padding(indicatorPadding),
                    horizontalArrangement = indicatorArrangement
                ) {
                    this@run.forEachIndexed { index, widget ->
                        widget.invoke(index == currentIndex.value)
                    }
                }

                Direction.LEFT,
                Direction.RIGHT -> Column(
                    modifier = Modifier
                        .constrainAs(indicators) {
                            if (indicatorDirection == Direction.LEFT) {
                                start.linkTo(itemView.start)
                            } else {
                                end.linkTo(itemView.end)
                            }
                            bottom.linkTo(itemView.bottom)
                            top.linkTo(itemView.top)
                        }
                        .padding(indicatorPadding),
                    verticalArrangement = indicatorArrangement
                ) {
                    this@run.forEachIndexed { index, widget ->
                        widget.invoke(index == currentIndex.value)
                    }
                }
            }
        }
    }
}


