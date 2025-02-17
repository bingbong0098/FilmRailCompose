package com.platform.mediacenter.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.platform.mediacenter.ui.theme.bottomBar
import com.platform.mediacenter.R
import com.platform.mediacenter.navigation.Screen

@Composable
fun AppTopAppBar(navController: NavHostController,onMenuClick : () -> Unit) {


    val showRoutes = listOf(
        Screen.Home.route,
        Screen.Movie.route,
        Screen.Series.route,
        Screen.LiveTv.route,
        Screen.Favorite.route,
        Screen.Blog.route,
    )
    val routeToTitleMap = mapOf(
        Screen.Home.route to R.string.home,
        Screen.Movie.route to R.string.movie,
        Screen.Series.route to R.string.series,
        Screen.LiveTv.route to R.string.tv,
        Screen.Blog.route to R.string.blog,
        Screen.Favorite.route to R.string.favorite,
    )
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route


    val showTopAppBar = currentRoute in showRoutes

    if (showTopAppBar) {
        val pageTitleRes = routeToTitleMap[currentRoute] ?: R.string.app_name
        TopAppBar(
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        style = MaterialTheme.typography.h4,
                        text = stringResource(id = pageTitleRes)
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.bottomBar,
            actions = {
                IconButton(onClick = {
                    navController.navigate(Screen.Search.route)
                }) {
                    Icon(Icons.Filled.Search, contentDescription = null)
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    onMenuClick()
                }) {
                    Icon(Icons.Filled.Menu, contentDescription = null)
                }
            }
        )
    }
}
