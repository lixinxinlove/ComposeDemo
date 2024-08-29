package com.lixinxin.composedemo.ui.screen.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lixinxin.composedemo.ui.screen.navController
import com.lixinxin.composedemo.ui.screen.route.ScreenRoute

@Composable
fun HomeScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        Column {

            Text(text = "HomeScreen")

            TextButton(onClick = {

                navController.navigate(ScreenRoute.CanvasDemo01Route.route)

            }) {
                Text(text = "绘制")
            }


        }





    }

}