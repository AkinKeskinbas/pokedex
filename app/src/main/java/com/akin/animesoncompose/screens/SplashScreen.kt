package com.akin.animesoncompose.screens

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.akin.animesoncompose.navigation.Router

@Composable
fun SplashScreen(router: Router) {
    //router.goToHomeScreen()
    LaunchedEffect(Unit){
        router.goToHomeScreen()
    }

}