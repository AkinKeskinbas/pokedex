package com.akin.animesoncompose.navigation

interface Router {
   // fun goToGameScreen(arg: Any?)
    fun goToSplashScreen()
    fun goToHomeScreen()
    fun goToLoginScreen()
    fun goToSignUpScreen()
    fun goToProfileScreen()
    fun goToBoardScreen()
    fun goToPrivateMessageScreen(writerId:String,messageContent:String)
    fun goBack()
}