package com.lixinxin.composedemo.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.reflect.KFunction1

@Composable
fun RootScreen() {

    var selectIndex by remember { mutableIntStateOf(0) }

    fun onTab(index: Int) {
        selectIndex = index;
    }

    Scaffold(
        content = { innerPadding -> RootContent(innerPadding, selectIndex) },
        topBar = { TopBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, "Add")
            }
        },
        bottomBar = {
            BottomBar(::onTab)
        }

    )
}

@Composable
fun RootContent(innerPadding: PaddingValues, index: Int) {
    Box(modifier = Modifier.padding(innerPadding)) {
        Text(text = "$index")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("Top app bar")
        }
    )
}


@Composable
fun BottomBar(onTab: KFunction1<Int, Unit>) {
    var selectIndex by remember { mutableIntStateOf(0) }
    val labelList = listOf("Home", "My")
    val iconList = listOf(Icons.Default.Home, Icons.Default.AccountBox)


    NavigationBar(contentColor = MaterialTheme.colorScheme.primaryContainer) {
        labelList.forEachIndexed { index, s ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedTextColor = Color.Black,
                    selectedIconColor = Color.Black,
                    unselectedTextColor = Color.Gray,
                    unselectedIconColor = Color.Gray,
                ),
                label = { Text(text = s) },
                icon = {
                    Icon(
                        imageVector = iconList[index],
                        contentDescription = ""
                    )
                },
                selected = index == selectIndex,

                onClick = {
                    selectIndex = index
                    onTab(selectIndex)
                }

            )
        }
    }
}






