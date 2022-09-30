package com.akin.casestudy.extansions

import androidx.compose.foundation.lazy.LazyListState
import com.akin.casestudy.util.Constants

fun String.parseUrlToNumber(): Int {
    val number = if (this.endsWith("/")) {
        this.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        this.takeLastWhile { it.isDigit() }
    }
    return number.toInt()
}
fun String.parseUrlToImage(): String {
    val number = this.parseUrlToNumber()
    return "${Constants.BASE_IMAGE_URL}${number}.png"
}

fun LazyListState.isScrolledToTheEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1