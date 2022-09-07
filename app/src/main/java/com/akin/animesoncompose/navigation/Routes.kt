package com.akin.animesoncompose.navigation

object Routes {
    const val HOME = "HOME"
    //const val GAME = "GAME_SCREEN/{userId}"
    const val SETTINGS = "SETTINGS"
    const val SPLASH = "SPLASH"
    const val LOGIN = "LOGIN"
    const val SIGNUP = "SIGNUP"
    const val PROFILE = "PROFILE"
    const val MY_MESSAGES = "MY_MESSAGES/{writerId}/{messageContent}"
    const val BOARD= "BOARD"
    const val PURCHASE = "PURCHASE"
    const val LANDING = "LANDING"
}


enum class Path(
    val path: String,
    val hasDeeplink:Boolean = false
) {
    HOME(path = Routes.HOME),
    SETTINGS(path = Routes.SETTINGS),
    SPLASH(path = Routes.SPLASH),
    LOGIN(path = Routes.LOGIN),
    SIGNUP(path = Routes.SIGNUP),
    PROFILE(path = Routes.PROFILE),
    MY_MESSAGES(path = Routes.MY_MESSAGES, hasDeeplink = true),
    BOARD(path = Routes.BOARD, hasDeeplink = true),
    PURCHASE(path = Routes.PURCHASE, hasDeeplink = true),
    LANDING(path = Routes.LANDING)
}
