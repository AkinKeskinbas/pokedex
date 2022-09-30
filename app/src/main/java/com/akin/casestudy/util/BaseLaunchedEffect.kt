package com.akin.casestudy.util

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.CoroutineScope

@Composable
fun BaseLaunchedEffect(
    key1: Any? = Unit,
    block: suspend CoroutineScope.(isInit: Boolean) -> Unit,
) {
    var isInit by rememberSaveable { mutableStateOf(true) }
    LaunchedEffect(key1) {
        block.invoke(this, isInit)
        isInit = false
    }
}