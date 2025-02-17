package com.platform.mediacenter.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.platform.mediacenter.R
import com.platform.mediacenter.data.model.home.HomeContentResponse
import com.platform.mediacenter.navigation.Screen
import com.platform.mediacenter.ui.screens.movie.MovieItemBox
import com.platform.mediacenter.ui.theme.LocalSpacing

@Composable
fun GenreListHomeBox(navController : NavHostController, title: String, list: List<HomeContentResponse.FeaturesGenreAndMovie.Video?>) {
    Column {

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
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold, text = title
            )

            Text(
                style = MaterialTheme.typography.h6,
                color = Color.Gray,
                text = stringResource(R.string.more)
            )
        }

        LazyRow(modifier = Modifier.fillMaxWidth().padding(top = LocalSpacing.current.medium)) {
            itemsIndexed(list) { index, item ->

//                val video = item?.videos?.get(index)
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
                    name = list[index]?.title.toString(),
                    year = list[index]?.release.toString(),
                    quality = list[index]?.videoQuality.toString(),
                    imdbRate = list[index]?.imdbRating.toString(),
                    videoDescadd = list[index]?.videoDescadd.toString()
                ) {
                    val actionType = if(list[index]!!.isTvseries == "1") "tvseries" else "movie"

                    navController.navigate(
                        Screen.Detail.withArg(
                            list[index]!!.videosId!!,actionType
                        )
                    )
                }

            }
        }
    }
}