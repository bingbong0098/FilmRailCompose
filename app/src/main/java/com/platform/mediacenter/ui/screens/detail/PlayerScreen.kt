package com.platform.mediacenter.ui.screens.detail

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.platform.mediacenter.R
import com.platform.mediacenter.ui.theme.colorPrimary
import kotlinx.coroutines.delay

@Composable
fun PlayerScreen(navController: NavHostController) {
    val context = LocalContext.current
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri("https://s3.distam.site/dl/Trailer/Announce%20Jansakht%20Final.mp4"))
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                PlayerView(it).apply {
                    this.player = player
                    useController = true
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
        }
    }
}


@Composable
fun CustomPlayerScreen(navController: NavHostController, url: String) {
    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(Unit) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
    DisposableEffect(Unit) {
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            playWhenReady = true
        }
    }
    val window = (context as? Activity)?.window
    window?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val insetsController = WindowInsetsControllerCompat(it, it.decorView)
            insetsController.hide(WindowInsetsCompat.Type.statusBars()) // مخفی کردن نوار وضعیت
        } else {
            it.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN // برای نسخه‌های قدیمی‌تر
        }
    }


    val isFullScreen = remember { mutableStateOf(true) }
    val isControlsVisible = remember { mutableStateOf(true) }
    val isPlaying = remember { mutableStateOf(player.isPlaying) }
    val progress = remember { mutableStateOf(0f) }
    val currentPosition = remember { mutableStateOf(0L) }
    val duration = remember { mutableStateOf(0L) }

    // تنظیم وضعیت اندازه ویدیو (فول اسکرین)
    val playerView = remember { PlayerView(context) }

    if (isControlsVisible.value) {
        LaunchedEffect(true) {
            delay(30000)
            isControlsVisible.value = false
        }
    }
    if (isFullScreen.value){
        LaunchedEffect(player) {
            playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            player.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
        }
    }else{
        LaunchedEffect(player) {
            playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            player.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        }
    }

    LaunchedEffect(player) {
        while (true) {
            currentPosition.value = player.currentPosition
            duration.value = player.duration
            progress.value = if (player.duration > 0) {
                player.currentPosition.toFloat() / player.duration.toFloat()
            } else 0f
            delay(500)
        }
    }

    // نوار پیشرفت را به‌روز می‌کنیم
    LaunchedEffect(player) {
        while (true) {
            progress.value = player.currentPosition.toFloat() / player.duration.toFloat()
            delay(500)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .clickable {

            isControlsVisible.value = !isControlsVisible.value
        }) {
        AndroidView(
            factory = { context ->
                playerView.apply {
                    this.player = player
                    useController = false // غیر فعال کردن کنترل‌های پیش‌فرض
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        Box(modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(20.dp)
            .size(40.dp)) {
            Image(painter = painterResource(R.drawable.colorpalette08), contentDescription = null)
        }

        // کنترلرهای سفارشی
        if (isControlsVisible.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(modifier = Modifier.align(Alignment.Center)) {

                    Box(
                        modifier = Modifier

                            .clip(CircleShape)
                            .clickable {
                                val newPosition = player.currentPosition - 15000 // 15 ثانیه جلوتر
                                player.seekTo(newPosition.coerceAtMost(player.duration)) // اطمینان از اینکه از مدت ویدیو بیشتر نشود

                            }
                            .background(Color.LightGray.copy(alpha = 0.3f))
                            .padding(5.dp)

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_forward_10_white),
                            contentDescription = "Play/Pause",
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))
                    Box(
                        modifier = Modifier

                            .clip(CircleShape)
                            .clickable {
                                if (player.isPlaying) {
                                    player.pause()
                                } else {
                                    player.play()
                                }
                                isPlaying.value = player.isPlaying
                            }
                            .background(Color.LightGray.copy(alpha = 0.3f))
                            .padding(5.dp)

                    ) {
                        Icon(
                            imageVector = if (isPlaying.value) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Play/Pause",
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                val newPosition = player.currentPosition + 15000 // 15 ثانیه
                                player.seekTo(newPosition.coerceAtMost(player.duration)) // اطمینان از اینکه از مدت ویدیو بیشتر نشود

                            }
                            .background(Color.LightGray.copy(alpha = 0.3f))
                            .padding(5.dp)

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_replay_10_white),
                            contentDescription = "Play/Pause",
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "قسمت بعدی",
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(colorPrimary.copy(alpha = 0.3f))
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )

                    Text(
                        text = "رد کردن تیتراژ",
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(colorPrimary.copy(alpha = 0.3f))
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xCC000000)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        painter = painterResource(R.drawable.ic_full),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                    Icon(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        painter = painterResource(R.drawable.ic_tune_vertical),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                    Icon(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        painter = painterResource(R.drawable.ic_subtitle),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                    Icon(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        painter = painterResource(R.drawable.ic_settings_white),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                    Slider(

                        value = progress.value,
                        onValueChange = { newValue ->
                            val newPosition = (newValue * player.duration).toLong()
                            player.seekTo(newPosition)
                            progress.value = newValue
                        },
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(horizontal = 4.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = Color.White,
                            activeTrackColor = Color.Red,
                            inactiveTrackColor = Color.Gray
                        )
                    )
                    Text(
                        text = formatTime(currentPosition.value),
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
//                    Icon(
//                        modifier = Modifier.padding(horizontal = 4.dp),
//                        painter = painterResource(R.drawable.ic_open_in_new_white),
//                        contentDescription = "Back",
//                        tint = Color.White
//                    )
                    Icon(
                        modifier = Modifier.padding(horizontal = 4.dp).clickable {
                            isFullScreen.value = !isFullScreen.value
                        },
                        painter = painterResource(R.drawable.ic_aspect_ratio_black_24dp),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                    Icon(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        painter = painterResource(R.drawable.ic_volume_up_white),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        }

    }
}

fun formatTime(timeMillis: Long): String {
    val totalSeconds = (timeMillis / 1000).toInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

