package com.akin.animesoncompose.navigation


sealed class Screens(
    val route: String
) {
    object Home : Screens(route = Path.HOME.path)
    object Login : Screens(route = Path.LOGIN.path)
    object SignUp : Screens(route = Path.SIGNUP.path)
    object Profile : Screens(route = Path.PROFILE.path)
    object Splash : Screens(route = Path.SPLASH.path)
    object Board : Screens(route = Path.BOARD.path)
    object MessageBox : Screens(route = Path.MY_MESSAGES.path){
        fun passId(writerId:String, messageContent:String):String{
            return "MY_MESSAGES/$writerId/$messageContent"
        }
    }
//    object GameScreen : Screens(route = Path.GAME.path) {
//        fun passId(userId:String):String{
//            return "GAME_SCREEN/$userId"
//        }
//    }
}
