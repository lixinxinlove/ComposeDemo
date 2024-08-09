package com.lixinxin.composedemo.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lixinxin.composedemo.ui.screen.route.ScreenRoute

@Composable
fun NavHostScreen() {


    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = ScreenRoute.WelcomeRoute.route) {

        composable(route = ScreenRoute.WelcomeRoute.route) { WelcomeScreen(navController) }
        composable(route = ScreenRoute.RootRoute.route) { RootScreen() }

    }


}