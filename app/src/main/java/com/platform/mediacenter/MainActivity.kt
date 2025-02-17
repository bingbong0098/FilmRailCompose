package com.platform.mediacenter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.platform.mediacenter.navigation.BottomNavigationBar
import com.platform.mediacenter.navigation.SetupNavGraph
import com.platform.mediacenter.ui.components.AppConfig
import com.platform.mediacenter.ui.components.AppTopAppBar
import com.platform.mediacenter.ui.components.ChangeStatusBarColor
import com.platform.mediacenter.ui.components.DrawerContent
import com.platform.mediacenter.ui.theme.MediaCenterTheme
import com.platform.mediacenter.utils.Constants.IS_DARK_THEME
import com.platform.mediacenter.utils.Constants.PERSIAN_LANG
import com.platform.mediacenter.utils.Constants.USER_LANGUAGE
import com.platform.mediacenter.utils.LocaleUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    @SuppressLint(
        "UnusedMaterial3ScaffoldPaddingParameter",
        "UnusedMaterialScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppConfig()
            val isDarkTheme = remember { mutableStateOf(IS_DARK_THEME) }
            MediaCenterTheme(isDarkTheme.value) {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                navController = rememberNavController()
                ChangeStatusBarColor(navController)


                Log.e("4718", "User Language: $USER_LANGUAGE")

                LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

                val direction = if (USER_LANGUAGE == PERSIAN_LANG) {
                    LayoutDirection.Rtl
                } else {
                    LayoutDirection.Ltr
                }

                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    ModalDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            DrawerContent(navController, isDarkTheme = isDarkTheme,
                                onMenuClick = {
                                    scope.launch {
                                        drawerState.close()
                                    }
                                })
                        },
                        content = {
                            Scaffold(
                                bottomBar = {
                                    BottomNavigationBar(
                                        navController = navController,
                                        onItemClick = {
                                            navController.navigate(it.route) {
                                                popUpTo(it.route) {
                                                    inclusive = false
                                                }
                                                launchSingleTop = true
                                            }
                                        }
                                    )
                                },
                                topBar = {
                                    AppTopAppBar(
                                        navController = navController,
                                        onMenuClick = {
                                            scope.launch {
                                                drawerState.open()
                                            }
                                        }
                                    )
                                },
                                content = {
                                    SetupNavGraph(navController)
                                }
                            )
                        }
                    )
                }
            }
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        Log.e("4718", "onBackPressed: " )
    }
}