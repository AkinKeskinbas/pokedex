package com.akin.casestudy.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.akin.casestudy.navigation.Router

@Composable
fun SplashScreen(router: Router) {
    //router.goToHomeScreen()
    LaunchedEffect(Unit){
        router.goToHomeScreen()
    }

}