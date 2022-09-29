package com.akin.casestudy.navigation

interface Router {
   // fun goToGameScreen(arg: Any?)
    fun goToSplashScreen()
    fun goToHomeScreen()
    fun goToDetailScreen()
    fun goBack()
}