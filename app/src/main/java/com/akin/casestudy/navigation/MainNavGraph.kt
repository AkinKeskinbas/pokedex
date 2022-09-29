package com.akin.casestudy.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.akin.casestudy.screens.DetailScreen
import com.akin.casestudy.screens.home.HomeScreen
import com.akin.casestudy.screens.splash.SplashScreen
import com.akin.casestudy.util.Constants.DETAILS_ARGUMENTS_KEY
import com.akin.casestudy.util.Constants.EMPTY_STRING
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(
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
    composable(
        deepLinks = listOf(NavDeepLink(DeepLinks.DETAIL_SCREEN_DEEPLINK.uri)),
        route = Screens.Detail.route,
        arguments = listOf(navArgument(DETAILS_ARGUMENTS_KEY){
            type = NavType.StringType
        })
    ) { backstackEntry ->
        val pokeId = backstackEntry.arguments?.getString(DETAILS_ARGUMENTS_KEY) ?: EMPTY_STRING
        DetailScreen(router = router, pokeId = pokeId)
    }
}

