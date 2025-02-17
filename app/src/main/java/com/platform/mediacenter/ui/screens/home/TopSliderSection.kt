package com.platform.mediacenter.ui.screens.home

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.platform.mediacenter.ui.theme.blackWindowLight
import com.platform.mediacenter.ui.theme.colorPrimary
import com.platform.mediacenter.data.model.home.HomeContentResponse
import com.platform.mediacenter.navigation.Screen
import com.platform.mediacenter.ui.theme.LocalSpacing
import com.platform.mediacenter.ui.theme.spacing
import com.platform.mediacenter.viewmodel.HomeViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopSliderSection(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    var sliderList by remember {
        mutableStateOf<List<HomeContentResponse.Slider.Slide?>>(emptyList())
    }

    val result by viewModel.homeContent.collectAsState()
    sliderList = result.data?.slider?.slide ?: emptyList()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
    ) {
        val pagerState = rememberPagerState()
        var imageUrl by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .padding(vertical = LocalSpacing.current.extraSmall)
        ) {


            HorizontalPager(
                count = sliderList.size,
                state = pagerState,
//                contentPadding = PaddingValues(horizontal = LocalSpacing.current.medium),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)

            ) { index ->

                imageUrl = sliderList[index]?.imageLink ?: ""
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .apply(
                                block = fun ImageRequest.Builder.() {
                                    scale(coil.size.Scale.FILL)
                                }
                            )
                            .build()
                    )
                    Image(
                        painter = painter,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize().clickable {
                            navController.navigate(
                                Screen.Detail.withArg(
                                    sliderList[index]!!.id!!,sliderList[index]!!.actionType!!
                                )
                            )
                        },
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        blackWindowLight,
                                        Color.Transparent,
                                        Color.Transparent
                                    ),
                                    startY = Float.POSITIVE_INFINITY,
                                    endY = 0f
                                )
                            )
                            .padding(top = 25.dp)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.small),
                        text = sliderList[index]?.title ?: "",
                        style = MaterialTheme.typography.h3,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                }

            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = LocalSpacing.current.small),
                activeColor = colorPrimary,
                inactiveColor = Color.Gray,
                indicatorWidth = LocalSpacing.current.extraSmall,
                indicatorHeight = LocalSpacing.current.extraSmall,
                indicatorShape = CircleShape
            )

        }
        LaunchedEffect(key1 = pagerState.currentPage) {
            delay(4000)
            var newPosition = pagerState.currentPage + 1
            if (newPosition > sliderList.size - 1) newPosition = 0
            pagerState.scrollToPage(newPosition)

        }

    }

}