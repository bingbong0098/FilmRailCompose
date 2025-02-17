package com.platform.mediacenter.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.platform.mediacenter.ui.theme.blackWindowLight
import com.platform.mediacenter.ui.theme.bottomBar
import com.platform.mediacenter.ui.theme.colorPrimary
import com.platform.mediacenter.ui.theme.selectedBottomBar
import com.platform.mediacenter.ui.theme.unSelectedBottomBar
import com.platform.mediacenter.R
import com.platform.mediacenter.ui.theme.darkText
import com.platform.mediacenter.utils.Constants.USER_LANGUAGE
import com.platform.mediacenter.utils.LocaleUtils


@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)
    val items = listOf(

        BottomNavItem(
            name = stringResource(id = R.string.home),
            route = Screen.Home.route,
            selectedIcon = painterResource(id = R.drawable.icon_home),
            deSelectedIcon = painterResource(id = R.drawable.icon_home)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.movie),
            route = Screen.Movie.route,
            selectedIcon = painterResource(id = R.drawable.icon_movie),
            deSelectedIcon = painterResource(id = R.drawable.icon_movie)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.series),
            route = Screen.Series.route,
            selectedIcon = painterResource(id = R.drawable.icon_tv_series),
            deSelectedIcon = painterResource(id = R.drawable.icon_tv_series)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.tv),
            route = Screen.LiveTv.route,
            selectedIcon = painterResource(id = R.drawable.icon_tv),
            deSelectedIcon = painterResource(id = R.drawable.icon_tv)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.favorite),
            route = Screen.Favorite.route,
            selectedIcon = painterResource(id = R.drawable.icon_favorite),
            deSelectedIcon = painterResource(id = R.drawable.icon_favorite)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.blog),
            route = Screen.Blog.route,
            selectedIcon = painterResource(id = R.drawable.ic_icons8_documents_80),
            deSelectedIcon = painterResource(id = R.drawable.ic_icons8_documents_80)
        )


    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in items.map { it.route }

    if (showBottomBar) {

        BottomNavigation(
            modifier = Modifier,
            backgroundColor = MaterialTheme.colors.bottomBar,
            elevation = 5.dp
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.selectedBottomBar,
                    unselectedContentColor = MaterialTheme.colors.unSelectedBottomBar,
                    icon = {

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (selected) {
                                Icon(
                                    modifier = Modifier.height(24.dp),
                                    painter = item.selectedIcon,
                                    contentDescription = ""
                                    , tint = colorPrimary
                                )
                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.h6,
                                    color = colorPrimary
                                )
                            } else {
                                Icon(
                                    modifier = Modifier.height(24.dp),
                                    painter = item.deSelectedIcon,
                                    contentDescription = ""
                                    , tint = MaterialTheme.colors.darkText
                                )
                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.h6
                                    , color = MaterialTheme.colors.darkText
                                )
                            }


                        }
                    }
                )

            }
        }
    }


}