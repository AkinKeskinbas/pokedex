package com.akin.animesoncompose.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.akin.animesoncompose.screens.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimesonNavGraph(
    navController: NavHostController,
    router: Router,
    startDestination: String = Screens.Splash.route,
    navGraphBuilder: NavGraphBuilder.() -> Unit = {},
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Start) },
        popExitTransition = { slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Start) }
    ) {
        navGraphBuilder.invoke(this)
        mainNavigation(router)
    }

}

@ExperimentalAnimationApi
private fun NavGraphBuilder.mainNavigation(
    router: Router,
) {
    composable(
        route = Screens.Splash.route,

        ) {
        SplashScreen(router = router)
    }
    composable(
        deepLinks = listOf(NavDeepLink(DeepLinks.HOME_SCREEN_DEEPLINK.uri)),
        route = Screens.Home.route,

        ) {
        HomeScreen(router = router)
    }

//    composable(
//        deepLinks = listOf(NavDeepLink(DeepLinks.MESSAGE_BOX_SCREEN_DEEPLINK.uri)),
//        route = Screens.MessageBox.route,
//
//        ) { backStackEntry ->
//        val id = backStackEntry.arguments?.getString("writerId") ?: "-1"
//        val messageContent = backStackEntry.arguments?.getString("messageContent") ?: ""
//        MessageScreen(router = router, writerId = id, messageContent = messageContent)
//    }
    composable(
        deepLinks = listOf(NavDeepLink(DeepLinks.PROFILE_SCREEN_DEEPLINK.uri)),
        route = Screens.Profile.route,


        ) {
        ProfileScreen(router = router)
    }
    composable(
        deepLinks = listOf(NavDeepLink(DeepLinks.SIGNUP_SCREEN_DEEPLINK.uri)),
        route = Screens.SignUp.route,


        ) {
        SignUpScreen(router = router)
    }
    composable(
        deepLinks = listOf(NavDeepLink(DeepLinks.LOGIN_SCREEN_DEEPLINK.uri)),
        route = Screens.Login.route,


        ) {
        LoginScreen(router = router)
    }

//    composable(
//        route = Screens.GameScreen.route,
//        enterTransition = { slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Start) },
//        popExitTransition = { slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Start) },
//        arguments = listOf(navArgument("userId") {
//            type = NavType.StringType
//            defaultValue = EMPTY_STRING
//        })
//
//    ) { backStackEntry ->
//        val id = backStackEntry.arguments?.getString("userId") ?: EMPTY_STRING
//        GameScreen(
//            router = router,
//            userId = id
//        )
//    }
}

