package com.akin.animesoncompose.navigation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.akin.animesoncompose.navigation.Screens


object NavBarItems {
    val BarItems = listOf(
        BottomBarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = Screens.Home.route
        ),
        BottomBarItem(
            title = "Board",
            image = Icons.Filled.DeveloperBoard,
            route = Screens.Board.route
        ),
        BottomBarItem(
            title = "Messages",
            image = Icons.Filled.Message,
            route = Screens.MessageBox.route
        ),
        BottomBarItem(
            title = "Profile",
            image = Icons.Filled.Message,
            route = Screens.Profile.route
        ),
    )
}