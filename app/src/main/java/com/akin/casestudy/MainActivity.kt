package com.akin.casestudy

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import com.akin.casestudy.navigation.MainNavGraph
import com.akin.casestudy.navigation.Router
import com.akin.casestudy.navigation.RouterImpl
import com.akin.casestudy.navigation.Screens
import com.akin.casestudy.ui.theme.AnimesonComposeTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val route = navBackStackEntry?.destination?.route ?: Screens.Splash.route
            val router: Router = remember { RouterImpl(navController, route) }

            AnimesonComposeTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    content = {
                        Surface(color = MaterialTheme.colorScheme.background) {
                            MainNavGraph(navController = navController, router = router)
                        }
                    }

                )
            }
        }
    }
}

