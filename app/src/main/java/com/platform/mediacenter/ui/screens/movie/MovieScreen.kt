package com.platform.mediacenter.ui.screens.movie

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.platform.mediacenter.R
import com.platform.mediacenter.data.model.movie.MovieResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.navigation.Screen
import com.platform.mediacenter.utils.Constants.USER_LANGUAGE
import com.platform.mediacenter.utils.LocaleUtils
import com.platform.mediacenter.viewmodel.MovieViewModel
import kotlinx.coroutines.launch


@Composable
fun MovieScreen(navController: NavController, viewModel: MovieViewModel = hiltViewModel()) {
    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)
    LaunchedEffect(true) {
        viewModel.getMoviesContent(1)
    }

    var movieList by remember {
        mutableStateOf<List<MovieResponse.MovieResponseItem?>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val movieResult by viewModel.movieContent.collectAsState()
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            refreshScope.launch {
                viewModel.getMoviesContent(1)
                Log.e("4718", "SwipeRefresh ")
            }
        }) {

        when (movieResult) {
            is NetworkResult.Success -> {


                movieList = movieResult.data ?: emptyList()
                Log.e("4718", "MovieScreen Success : ${movieList[0]?.title} ")

                LazyVerticalGrid(
                    modifier = Modifier.padding(bottom = 55.dp),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    itemsIndexed(movieList) { index, item ->
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
                            name = movieList[index]?.title.toString(),
                            year = movieList[index]?.release.toString(),
                            quality = movieList[index]?.videoQuality.toString(),
                            imdbRate = movieList[index]?.imdbRating.toString(),
                            videoDescadd = movieList[index]?.videoDescadd.toString()
                        ) {


                            navController.navigate(
                                Screen.Detail.withArg(
                                    movieList[index]!!.videosId!!,"movie"
                                )
                            )                        }
                    }
                }
                loading = false
            }

            is NetworkResult.Error -> {
                loading = false
                Log.e("3636", "MovieScreen Error : ${movieResult.message} ")
            }

            is NetworkResult.Loading -> {
                loading = true
            }
        }
    }



}