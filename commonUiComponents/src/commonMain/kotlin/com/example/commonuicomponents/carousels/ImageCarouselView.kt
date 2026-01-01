package com.example.commonuicomponents.carousels

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.commonuicomponents.commonWidgets.RandomColorBox
import com.example.commonuicomponents.helpers.Direction
import com.example.commonuicomponents.helpers.IndicatorType
import com.example.commonuicomponents.helpers.ScrollType

@Composable
fun ImageCarouselView (
    modifier: Modifier = Modifier,
    listOfImagePaths: List<String>,
    indexOfItemInHighlight: MutableState<Int>? = null,
    indicatorWidgetType: List<IndicatorType> = emptyList(),
    indicatorDirection: Direction = Direction.BOTTOM,
    scrollType: ScrollType = ScrollType.SnapInScrolledDirection,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    indicatorArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center,
    indicatorPadding: PaddingValues = PaddingValues(16.dp)
){
    CarouselView(
        modifier = modifier,
        items = getComposableViews(listOfImagePaths),
        indexOfItemInHighlight = indexOfItemInHighlight,
        indicatorWidget = getComposableIndicatorView(indicatorWidgetType),
        indicatorDirection = indicatorDirection,
        scrollType = scrollType,
        flingBehavior = flingBehavior,
        indicatorArrangement = indicatorArrangement,
        indicatorPadding = indicatorPadding
    )
}

fun getComposableViews(listOfImagePaths: List<String>): List<@Composable () -> Unit> {
    val listOfViews = mutableListOf<@Composable () -> Unit>()
    listOfImagePaths.forEach {
        listOfViews.add {
//            CustomImage(
//                path = it,
//                contentDescription = it
//            )
            RandomColorBox()
        }
    }
    return listOfViews
}

fun getComposableIndicatorView(
    indicatorTypes: List<IndicatorType>,
): List<@Composable (Boolean) -> Unit> {
    val listOfViews = mutableListOf<@Composable (Boolean) -> Unit>()
    indicatorTypes.forEach {
        listOfViews.add { isSelected ->
            IndicatorWidgetProvider(
                type = it,
                isSelected = isSelected
            )
        }
    }
    return listOfViews
}


