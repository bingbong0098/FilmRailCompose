package com.platform.mediacenter.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.platform.mediacenter.R
import com.platform.mediacenter.data.model.home.HomeContentResponse
import com.platform.mediacenter.navigation.Screen
import com.platform.mediacenter.ui.screens.movie.MovieItemBox
import com.platform.mediacenter.ui.theme.LocalSpacing
import com.platform.mediacenter.viewmodel.HomeViewModel


@Composable
fun LatestSeriesSection(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var latestSeriesList by remember {
        mutableStateOf<List<HomeContentResponse.LatestTvsery?>>(
            emptyList()
        )
    }
    val latestSeriesResult by viewModel.homeContent.collectAsState()
    latestSeriesList = latestSeriesResult.data?.latestTvseries ?: emptyList()

    if (latestSeriesList.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = LocalSpacing.current.medium,
                    start = LocalSpacing.current.small,
                    end = LocalSpacing.current.small
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.latest_tv_series),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )


            Text(
                text = stringResource(R.string.more),
                style = MaterialTheme.typography.h6,
                color = Color.Gray
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = LocalSpacing.current.medium)
        ) {

            itemsIndexed(
                latestSeriesList,
                key = { _, item -> item?.title.hashCode() }) { index, item ->
                val imageUrl = item?.thumbnailUrl ?: ""
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

                MovieItemBox(
                    image = painter,
                    name = latestSeriesList[index]?.title.toString(),
                    year = latestSeriesList[index]?.release.toString(),
                    quality = latestSeriesList[index]?.videoQuality.toString(),
                    imdbRate = latestSeriesList[index]?.imdbRating.toString(),
                    videoDescadd = latestSeriesList[index]?.videoDescadd.toString()
                ) {

                    navController.navigate(
                        Screen.Detail.withArg(
                            latestSeriesList[index]!!.videosId!!,"tvseries"
                        )
                    )
                }
            }
        }
    }
}