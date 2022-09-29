package com.akin.animesoncompose.navigation


sealed class Screens(
    val route: String
) {
    object Home : Screens(route = Path.HOME.path)
    object Splash : Screens(route = Path.SPLASH.path)
    object Detail : Screens(route = Path.DETAIL.path) {
        fun passID(pokeID: String): String {
            return "DETAIL/$pokeID"
        }
    }
}
