package com.platform.mediacenter.navigation


import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.platform.mediacenter.ui.screens.blog.BlogScreen
import com.platform.mediacenter.ui.screens.favorite.FavoriteScreen
import com.platform.mediacenter.ui.screens.home.HomeScreen
import com.platform.mediacenter.ui.screens.tv.LiveTvScreen
import com.platform.mediacenter.ui.screens.movie.MovieScreen
import com.platform.mediacenter.ui.screens.profile.ProfileScreen
import com.platform.mediacenter.ui.screens.search.SearchScreen
import com.platform.mediacenter.ui.screens.tvseries.SeriesScreen
import com.platform.mediacenter.ui.screens.splash.SplashScreen
import com.platform.mediacenter.ui.screens.country.CountryScreen
import com.platform.mediacenter.ui.screens.detail.CustomPlayerScreen
import com.platform.mediacenter.ui.screens.detail.DetailScreen
import com.platform.mediacenter.ui.screens.detail.PlayerScreen
import com.platform.mediacenter.ui.screens.devices.DeviceManagerScreen
import com.platform.mediacenter.ui.screens.profile.ForgetPassScreen
import com.platform.mediacenter.ui.screens.genre.GenreScreen
import com.platform.mediacenter.ui.screens.profile.LoginChooser
import com.platform.mediacenter.ui.screens.profile.SignUpScreen
import com.platform.mediacenter.ui.screens.more.MoreItemScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Devices.route
//        startDestination = Screen.Search.route
//        startDestination = Screen.Detail.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(route = Screen.Movie.route) {
            MovieScreen(navController)
        }

        composable(route = Screen.Series.route) {
            SeriesScreen(navController)
        }

        composable(route = Screen.Favorite.route) {
            FavoriteScreen(navController)
        }

        composable(route = Screen.LiveTv.route) {
            LiveTvScreen(navController)
        }

        composable(route = Screen.Blog.route) {
            BlogScreen()
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(route = Screen.Search.route) {
            SearchScreen(navController)
        }

        composable(route = Screen.Genre.route) {
            GenreScreen(navController)
        }

        composable(route = Screen.Country.route) {
            CountryScreen(navController)
        }

        composable(route = Screen.Detail.route  + "/{videoId}/{type}",
            arguments = listOf(
                navArgument("videoId") {
                    type = NavType.StringType
                },
                navArgument("type") {
                    type = NavType.StringType
                }
            )) {
            val videoId = it.arguments?.getString("videoId")
            val type = it.arguments?.getString("type")
            DetailScreen(navController = navController, videoId = videoId, type = type)
        }

        composable(route = Screen.Player.route + "/{url}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                }
            )
            ) {
            val videoUrl = it.arguments?.getString("url")?.let { encodedUrl -> Uri.decode(encodedUrl) } ?: ""
            CustomPlayerScreen(navController, videoUrl)
        }

        composable(route = Screen.StartLogin.route) {
            LoginChooser(navController)
        }

        composable(route = Screen.SignInEmail.route) {
            SignUpScreen()
        }

        composable(route = Screen.ForgetPass.route) {
            ForgetPassScreen()
        }

        composable(route = Screen.MoreItem.route) {
            MoreItemScreen(navController)
        }

        composable(route = Screen.Devices.route) {
            DeviceManagerScreen(navController)
        }

    }
}