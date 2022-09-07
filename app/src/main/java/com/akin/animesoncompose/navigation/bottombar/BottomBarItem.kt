package com.akin.animesoncompose.navigation.bottombar

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(
    val title: String,
    val image: ImageVector,
    val route: String,
    val isActive:Boolean = true
)