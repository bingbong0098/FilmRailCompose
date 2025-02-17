@file:Suppress("DEPRECATION")

package com.platform.mediacenter.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.platform.mediacenter.data.model.home.HomeContentResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.ui.components.HomeShimmerLoading
import com.platform.mediacenter.utils.Constants.USER_LANGUAGE
import com.platform.mediacenter.utils.LocaleUtils
import com.platform.mediacenter.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController)
}

@Composable
fun Home(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)
    LaunchedEffect(true) {
        refreshData(viewModel)
    }
    SwipeRefreshSection(viewModel, navController)
}

@Composable
fun SwipeRefreshSection(viewModel: HomeViewModel, navController: NavHostController) {
    val homeResult by viewModel.homeContent.collectAsState()

    var loading by remember {
        mutableStateOf(false)
    }
    var genreHomeList by remember {
        mutableStateOf<List<HomeContentResponse.FeaturesGenreAndMovie?>>(
            emptyList()
        )
    }
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = loading)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            refreshScope.launch {
                loading = true
                refreshData(viewModel)
                Log.e("4718", "SwipeRefresh ")
            }
        }) {

        when (homeResult) {
            is NetworkResult.Success -> {
                Log.e("4718", "Home Success : ${homeResult.message} ")
                genreHomeList = homeResult.data?.featuresGenreAndMovie ?: emptyList()

                if(loading){

                    HomeShimmerLoading()

                }else{
                    LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = 55.dp)) {
                        item { TopSliderSection(navController) }
                        item { PopularStarsSection(navController) }
                        item { TvChannelSection(navController) }
                        item { ContinueWatchingSection(navController) }
                        item { LatestMoviesSection(navController) }
                        item { LatestSeriesSection(navController) }

                        //Genre List Home Section
                        itemsIndexed(genreHomeList) { index, _ ->
                            GenreListHomeBox(navController,genreHomeList[index]?.name.toString(), genreHomeList[index]?.videos!!)
                        }
                    }
                }
            }
            is NetworkResult.Error -> {
                Log.e("4718", "Home Error : ${homeResult.message} ")
            }
            is NetworkResult.Loading -> {
                loading = true
            }
        }

    }
    LaunchedEffect(homeResult) {
        if (homeResult !is NetworkResult.Loading) {
            loading = false
        }
    }
}
private suspend fun refreshData(viewModel: HomeViewModel) {
    viewModel.getHomeContent()
}
