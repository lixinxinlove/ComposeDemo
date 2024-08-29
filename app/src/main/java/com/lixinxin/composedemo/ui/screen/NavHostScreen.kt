package com.lixinxin.composedemo.ui.screen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lixinxin.composedemo.ui.screen.canvas.CanvasDemo01
import com.lixinxin.composedemo.ui.screen.canvas.CanvasDemo01Preview
import com.lixinxin.composedemo.ui.screen.route.ScreenRoute

@SuppressLint("StaticFieldLeak")
lateinit var  navController : NavHostController

@Composable
fun NavHostScreen() {
    navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.WelcomeRoute.route) {
        composable(route = ScreenRoute.WelcomeRoute.route) { WelcomeScreen() }
        composable(route = ScreenRoute.RootRoute.route) { RootScreen() }
        composable(route = ScreenRoute.CanvasDemo01Route.route) { CanvasDemo01() }
    }
}