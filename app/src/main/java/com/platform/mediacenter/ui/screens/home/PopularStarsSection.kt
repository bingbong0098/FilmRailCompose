package com.platform.mediacenter.ui.screens.home

import android.util.Log
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
import com.platform.mediacenter.ui.theme.LocalSpacing
import com.platform.mediacenter.viewmodel.HomeViewModel


@Composable
fun PopularStarsSection(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    var popularStarsList by remember {
        mutableStateOf<List<HomeContentResponse.PopularStar?>>(
            emptyList()
        )
    }
    val popularStarsResult by viewModel.homeContent.collectAsState()
    popularStarsList = popularStarsResult.data?.popularStars ?: emptyList()

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = LocalSpacing.current.medium,
                start = LocalSpacing.current.small,
                end = LocalSpacing.current.small
            ),
        text = stringResource(R.string.popular_stars),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = LocalSpacing.current.medium)
    ) {

        itemsIndexed(popularStarsList, key = { _, item -> item?.starName.hashCode() }) { _, item ->
            val imageUrl = item?.imageUrl ?: ""
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

            PopularStarsItemBox(item?.starName.orEmpty(), painter) {

                navController.navigate(Screen.MoreItem.route)

            }
        }
    }

}