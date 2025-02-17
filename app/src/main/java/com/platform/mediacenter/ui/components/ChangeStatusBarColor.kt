package com.platform.mediacenter.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.platform.mediacenter.ui.theme.blackWindowLight
import com.platform.mediacenter.ui.theme.colorAccent
import com.platform.mediacenter.navigation.Screen


@Composable
fun ChangeStatusBarColor(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val systemUiController = rememberSystemUiController()
    val isLight = MaterialTheme.colors.isLight

    when (navBackStackEntry?.destination?.route) {
        Screen.Splash.route -> {
            LaunchedEffect(Unit) {
                systemUiController.setStatusBarColor(
                    color = Color.White
                )
            }
        }
        else -> {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = if (isLight) colorAccent else blackWindowLight
                )
            }
        }
    }

}