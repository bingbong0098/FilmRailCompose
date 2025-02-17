package com.platform.mediacenter.ui.screens.favorite

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.platform.mediacenter.R
import com.platform.mediacenter.data.model.Movie
import com.platform.mediacenter.data.model.movie.MovieResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.navigation.Screen
import com.platform.mediacenter.ui.screens.movie.MovieItemBox
import com.platform.mediacenter.ui.theme.LocalSpacing
import com.platform.mediacenter.ui.theme.colorPrimary
import com.platform.mediacenter.utils.Constants.USER_ID
import com.platform.mediacenter.utils.Constants.USER_LANGUAGE
import com.platform.mediacenter.utils.LocaleUtils
import com.platform.mediacenter.viewmodel.FavoriteViewModel

@Composable
fun FavoriteScreen(navController: NavController, viewModel: FavoriteViewModel = hiltViewModel()) {
    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)
    LaunchedEffect(true) {
        viewModel.getFavoriteList(1)
    }

    var favoriteList by remember {
        mutableStateOf<List<Movie?>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val favoriteResult by viewModel.favoriteList.collectAsState()
    when (favoriteResult) {
        is NetworkResult.Success -> {

            favoriteList = favoriteResult.data ?: emptyList()
            Log.e("4718", "FavoriteScreen Success : ${favoriteList.size} ")
            if (favoriteList.isEmpty() || USER_ID == "") {
                EmptyView()
            }else{
                LazyVerticalGrid(
                    modifier = Modifier.padding(bottom = 55.dp),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp)
                ) {

                    itemsIndexed(favoriteList) { index, item ->
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
                            name = favoriteList[index]?.title.toString(),
                            year = favoriteList[index]?.release.toString(),
                            quality = favoriteList[index]?.videoQuality.toString(),
                            imdbRate = favoriteList[index]?.imdbRating.toString(),
                            videoDescadd = favoriteList[index]?.videoDescadd.toString()
                        ) {

                            navController.navigate(Screen.Detail.route)
                        }
                    }
                }
            }


            loading = false
        }

        is NetworkResult.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (favoriteList.isEmpty()) {

                    Text(
                        modifier = Modifier.padding(horizontal = LocalSpacing.current.medium),
                        text = if (USER_ID.isEmpty()) "لطفا برای دیدن لیست علاقه مندی های خود وارد شوید" else "اینجا خبری نیست",
                        style = MaterialTheme.typography.h1,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Image(
                        modifier = Modifier,
                        painter = painterResource(R.drawable.bg_no_item_citynew),
                        contentDescription = null
                    )
                }

            }
            loading = false
            Log.e("4718", "FavoriteScreen Error : ${favoriteResult.message} ")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }
}


@Composable
fun EmptyView() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(horizontal = LocalSpacing.current.medium),
            text = if (USER_ID.isEmpty()) "لطفا برای دیدن لیست علاقه مندی های خود وارد شوید" else "اینجا خبری نیست",
            style = MaterialTheme.typography.h1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Image(
            modifier = Modifier,
            painter = painterResource(R.drawable.bg_no_item_citynew),
            contentDescription = null
        )


    }
}