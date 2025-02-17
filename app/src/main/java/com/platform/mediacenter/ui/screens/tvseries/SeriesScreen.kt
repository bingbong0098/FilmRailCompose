package com.platform.mediacenter.ui.screens.tvseries

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.platform.mediacenter.R
import com.platform.mediacenter.data.model.tvseries.TvSerisResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.navigation.Screen
import com.platform.mediacenter.ui.screens.movie.MovieItemBox
import com.platform.mediacenter.utils.Constants.USER_LANGUAGE
import com.platform.mediacenter.utils.LocaleUtils
import com.platform.mediacenter.viewmodel.TvSeriesViewModel

@Composable
fun SeriesScreen(navController: NavController, viewModel: TvSeriesViewModel = hiltViewModel()) {
    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)
    LaunchedEffect(true) {
        viewModel.getTvSeriesContent(1)
    }

    var seriesList by remember {
        mutableStateOf<List<TvSerisResponse.TvSerisResponseItem?>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val seriesResult by viewModel.tvSeriesContent.collectAsState()
    when (seriesResult) {
        is NetworkResult.Success -> {

            seriesList = seriesResult.data ?: emptyList()
            Log.e("4718", "SeriesScreen Success : ${seriesList[0]?.title} ")

            LazyVerticalGrid(
                modifier = Modifier.padding(bottom = 55.dp),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(4.dp)
            ) {
                itemsIndexed(seriesList) { index, item ->
                    val imageUrl = item?.thumbnailUrl ?: ""
                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .apply(
                                block = fun ImageRequest.Builder.() {
                                    scale(coil.size.Scale.FILL)
                                    placeholder(R.drawable.colorpalette08)
                                }
                            )
                            .build()
                    )

                    MovieItemBox(
                        image = painter,
                        name = seriesList[index]?.title.toString(),
                        year = seriesList[index]?.release.toString(),
                        quality = seriesList[index]?.videoQuality.toString(),
                        imdbRate = seriesList[index]?.imdbRating.toString(),
                        videoDescadd = seriesList[index]?.videoDescadd.toString()
                    ) {
                        navController.navigate(
                            Screen.Detail.withArg(
                                seriesList[index]!!.videosId!!,"tvseries"
                            )
                        )
                    }
                }
            }
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("4718", "SeriesScreen Error : ${seriesResult.message} ")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }
}