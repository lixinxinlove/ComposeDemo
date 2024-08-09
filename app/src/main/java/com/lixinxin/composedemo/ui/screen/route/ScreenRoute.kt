package com.lixinxin.composedemo.ui.screen.route

sealed class ScreenRoute(val route: String) {
    data object WelcomeRoute : ScreenRoute("welcome")
    data object RootRoute : ScreenRoute("root")

}
