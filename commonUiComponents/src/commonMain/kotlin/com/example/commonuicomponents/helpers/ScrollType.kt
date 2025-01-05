package com.example.commonuicomponents.helpers

sealed class ScrollType {
    data object Normal: ScrollType()

    data object SnapForward: ScrollType()

    data object SnapBackwards: ScrollType()

    data object SnapInScrolledDirection: ScrollType()

    data class AutoScrollLeft(
        val delayTimeInMillis: Long = 800L
    ): ScrollType()

    data class AutoScrollRight(
        val delayTimeInMillis: Long = 800L
    ): ScrollType()
}
