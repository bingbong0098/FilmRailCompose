@file:Suppress("DEPRECATION")

package com.platform.mediacenter.ui.screens.splash

import android.os.Build
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.platform.mediacenter.R
import com.platform.mediacenter.navigation.Screen
import com.platform.mediacenter.utils.Constants.USER_AGENT
import com.platform.mediacenter.viewmodel.DataStoreViewModel
import com.platform.mediacenter.viewmodel.ProfileViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    USER_AGENT =
        "AndroidMobile/" + Build.VERSION.RELEASE + " (" + Build.PRODUCT + "; " + Build.BRAND + ")"

    LaunchedEffect(Unit) {
        delay(6000)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
    Splash(navController = navController)
}

@Composable
fun Splash(
    viewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current
    val videoUri = "android.resource://${context.packageName}/${R.raw.splashvideo2}"
    LaunchedEffect (true){
        viewModel.checkLoginValid()
    }
    val loginValidResult by viewModel.isLoginValid.collectAsState()
    if (loginValidResult == "valid") {
        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
    } else if (loginValidResult == "invalid") {

        navController.navigate(Screen.Devices.route)

    } else if (loginValidResult == "401") {

        dataStoreViewModel.saveUserId("")
        dataStoreViewModel.saveAccessToken("")
        dataStoreViewModel.saveUserLoginStatus(false)
    }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
            playWhenReady = true
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        )

    }

}