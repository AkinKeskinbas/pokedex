package com.akin.animesoncompose

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.akin.animesoncompose.navigation.MainNavGraph
import com.akin.animesoncompose.navigation.Router
import com.akin.animesoncompose.navigation.RouterImpl
import com.akin.animesoncompose.navigation.Screens
import com.akin.animesoncompose.ui.theme.AnimesonComposeTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

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

