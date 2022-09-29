package com.akin.casestudy.navigation

object Routes {
    const val HOME = "HOME"
    const val SPLASH = "SPLASH"
    const val DETAIL = "DETAIL/{pokeId}"
}

enum class Path(
    val path: String,
    val hasDeeplink: Boolean = false
) {
    HOME(path = Routes.HOME),
    SPLASH(path = Routes.SPLASH),
    DETAIL(path = Routes.DETAIL, hasDeeplink = true)
}
