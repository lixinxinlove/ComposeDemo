package com.lixinxin.composedemo.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lixinxin.composedemo.R
import com.lixinxin.composedemo.ui.screen.route.ScreenRoute
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navController: NavHostController) {


    var name by rememberSaveable { mutableStateOf("") }


    LaunchedEffect(key1 = 1) {
        delay(3000)
        navController.navigate(ScreenRoute.RootRoute.route)
    }

    Surface {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
        ) {


            Column {


                Text(
                    text = name,
                    fontSize = 16.sp,
                    color = Color.Red,
                    fontStyle = FontStyle.Italic
                )


                Image(
                    painter = painterResource(id = R.mipmap.ic_girl),
                    contentDescription = stringResource(R.string.logo_name),
                    contentScale = ContentScale.Inside,
                    colorFilter = ColorFilter.tint(Color.Red, blendMode = BlendMode.Color)
                )
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 16.sp,
                    color = Color.Red,
                    fontStyle = FontStyle.Italic
                )


                Row(
                    modifier = Modifier.width(260.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Button(onClick = {
                        name = "lxx"
                        Log.e("", "Click Me")
                    }, border = BorderStroke(1.dp, Color.Red)) {
                        Text(text = "Click Me")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(

                        modifier = Modifier
                            .size(48.dp)
                            .background(color = Color.Red)
                            .clickable {

                            },
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "图标"
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(

                        modifier = Modifier
                            .size(48.dp)
                            .background(color = Color.Red),
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "图标"
                    )


                }
                InputName(name, onValueChange = { name = it })

            }


        }

    }

}


@Composable
fun InputName(name: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onValueChange,
        label = { Text("please input Name") }
    )

}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {

    WelcomeScreen(rememberNavController())
}