package com.akin.animesoncompose.navigation

import androidx.navigation.NavHostController

class RouterImpl(
    private val navHostController: NavHostController,
    private val startDestination: String = Routes.SPLASH
) : Router {


    override fun goToSplashScreen() {
        navigate(Screens.Splash,removeFromHistory = true, singleTop = true)
    }

    override fun goToHomeScreen() {
        navigate(Screens.Home, removeFromHistory = true, singleTop = true)
    }

    override fun goToLoginScreen() {
        navigate(Screens.Login, removeFromHistory = true, singleTop = true)
    }

    override fun goToSignUpScreen() {
        navigate(Screens.SignUp, removeFromHistory = true, singleTop = true)
    }

    override fun goToProfileScreen() {
        navigate(Screens.Profile)
    }

    override fun goToBoardScreen() {
        navigate(Screens.Board)
    }

    override fun goToPrivateMessageScreen(writerId:String,messageContent:String) {
        navigateArg(route = Screens.MessageBox.passId(writerId, messageContent))
    }


    override fun goBack() {
        navHostController.apply {
            navigateUp()
            navigate(startDestination) {
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    private fun navigate(
        screen: Screens,
        removeFromHistory: Boolean = false,
        singleTop: Boolean = false
    ) {
        navHostController.apply {
            navigate(screen.route) {
                if (removeFromHistory) {
                    if (singleTop) {
                        popUpTo(screen.route){
                            inclusive = true
                        }
                    } else {
                        popUpTo(0) {
                            saveState = false
                        }
                    }

                } else {
                    restoreState = true
                }
                launchSingleTop = singleTop
            }
        }
    }

    private fun navigateArg(
        route: String,
        removeFromHistory: Boolean = false,
        singleTop: Boolean = false
    ) {
        navHostController.apply {
            navigate(route = route) {
                if (removeFromHistory) {
                    if (singleTop) {
                        popUpTo(Screens.Home.route)
                    } else {
                        popUpTo(0) {
                            saveState = false
                        }
                    }

                } else {
                    restoreState = true
                }
                launchSingleTop = singleTop
            }
        }
    }

}
