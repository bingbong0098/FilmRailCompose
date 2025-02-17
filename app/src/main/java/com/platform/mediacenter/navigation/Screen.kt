package com.platform.mediacenter.navigation

sealed class Screen (val route : String){

    data object Splash : Screen(route = "splash_screen")
    data object Home : Screen(route = "home_screen")
    data object Movie : Screen(route = "movie_screen")
    data object Series : Screen(route = "series_screen")
    data object LiveTv : Screen(route = "tv_screen")
    data object Favorite : Screen(route = "favorite_screen")
    data object Blog : Screen(route = "blog_screen")
    data object Detail : Screen(route = "detail_screen")
    data object Player : Screen(route = "player_screen")
    data object PurchasePlan : Screen(route = "purchase_screen")
    data object Profile : Screen(route = "profile_screen")
    data object Search : Screen(route = "search_screen")
    data object Genre : Screen(route = "genre_screen")
    data object Country : Screen(route = "country_screen")
    data object StartLogin : Screen(route = "start_login_screen")
    data object SignInEmail : Screen(route = "sign_in_email_screen")
    data object ForgetPass : Screen(route = "forget_pass_screen")
    data object MoreItem : Screen(route = "more_item_screen")
    data object Devices : Screen(route = "devices_screen")

    fun withArg(vararg args:String):String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}