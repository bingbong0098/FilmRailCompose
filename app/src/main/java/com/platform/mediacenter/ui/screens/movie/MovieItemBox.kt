package com.platform.mediacenter.ui.screens.movie

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.platform.mediacenter.ui.theme.orange_dark
import com.platform.mediacenter.R
import com.platform.mediacenter.ui.theme.LocalShape
import com.platform.mediacenter.utils.Constants.ENGLISH_LANG
import com.platform.mediacenter.utils.Constants.USER_LANGUAGE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieItemBox(
    image: Painter,
    name: String,
    year: String,
    quality: String,
    imdbRate: String,
    videoDescadd: String,
    height: Int = 310,
    imageHeight : Int = 240,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(190.dp) // fillMaxWidth
            .height(height.dp)
            .padding(6.dp),
        shape = LocalShape.current.semiMedium,
        onClick = onClick
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.height(imageHeight.dp)) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                // imdb rate
                if (imdbRate.isNotEmpty()) {
                    Box(
                        contentAlignment = Alignment.TopStart,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth()
                    ) {
                        Card(
                            modifier = Modifier
                                .width(23.dp),
                            shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
                            backgroundColor = Color(0xfff5c518)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier.height(22.dp),
                                    painter = painterResource(R.drawable.imdb),
                                    contentDescription = null
                                )

                                Text(
                                    modifier = Modifier.padding(bottom = 2.dp),
                                    text = imdbRate,
                                    style = MaterialTheme.typography.h6,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }

                }


                // iranian movies mark
                if (videoDescadd == "ایرانی") {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.TopEnd
                    ) {
//                        Image(painter = painterResource(R.drawable.bg_gradient_ribbon), contentDescription = null)
                        AndroidView(
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp)
                                .graphicsLayer(
                                    rotationZ = if (USER_LANGUAGE == ENGLISH_LANG) 90f else 0f
                                ),
                            factory = { context ->
                                ImageView(context).apply {
                                    setBackgroundResource(R.drawable.bg_gradient_ribbon)
                                }
                            })

                        BasicText(
                            text = "ایرانی",
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier = Modifier
                                .graphicsLayer(
                                    rotationZ = if (USER_LANGUAGE == ENGLISH_LANG) 45f else -45f
                                )
                                .padding(top = 12.dp)
                        )
                    }

                }

                // video descadd
                if (videoDescadd != "ایرانی" && videoDescadd.isNotEmpty()) {
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxSize()
                    ) {
                        Card(
                            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                            backgroundColor = Color.Transparent
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        Brush.horizontalGradient(
                                            when (videoDescadd) {
                                                "زیرنویس چسبیده" -> {
                                                    listOf(
                                                        Color.Transparent,
                                                        Color(0xA3505050),
                                                        Color.Gray,
                                                        Color(0xA3505050),
                                                        Color.Transparent,
                                                    )
                                                }

                                                "دوبله" -> {
                                                    listOf(
                                                        Color.Transparent,
                                                        orange_dark,
                                                        orange_dark,
                                                        Color.Transparent,
                                                    )
                                                }

                                                "بزودی" -> {
                                                    listOf(
                                                        Color.Transparent,
                                                        Color(0xff003285),
                                                        Color.Transparent,
                                                    )
                                                }

                                                else -> {
                                                    listOf(
                                                        Color.Transparent,
                                                        orange_dark,
                                                        Color.Transparent,
                                                    )
                                                }
                                            }

                                        )
                                    )

                                    .border(
                                        shape = RoundedCornerShape(
                                            topStart = 10.dp,
                                            topEnd = 10.dp
                                        ), width = 0.2.dp,
                                        color = Color.Gray
                                    )
                                    .padding(vertical = 1.dp),
                                text = videoDescadd,
                                style = MaterialTheme.typography.h5,
                                fontSize = 12.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

            }

//            LinearProgressIndicator(0.75f, Modifier.fillMaxWidth(), color = colorPrimary)
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
//                val infiniteTransition = rememberInfiniteTransition(label = "")
//                val offsetX by infiniteTransition.animateFloat(
//                    initialValue = 0f,
//                    targetValue = -1000f, // مقدار را متناسب با طول متن تنظیم کنید
//                    animationSpec = infiniteRepeatable(
//                        animation = tween(durationMillis = 8000, easing = LinearEasing), // سرعت حرکت
//                        repeatMode = RepeatMode.Restart
//                    ), label = ""
//                )

                Text(
                    text = name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
//                    .graphicsLayer(translationX = offsetX)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (quality.isNotEmpty())
                        Text(quality)
                    if (year.isNotEmpty())
                        Text(year)
                }
            }
        }
    }
}