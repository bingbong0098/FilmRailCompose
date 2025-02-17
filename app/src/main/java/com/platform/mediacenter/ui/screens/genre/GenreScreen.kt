package com.platform.mediacenter.ui.screens.genre

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.platform.mediacenter.data.model.genre.GenreResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.viewmodel.GenreViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreScreen(navController: NavHostController, viewModel: GenreViewModel = hiltViewModel()) {

    LaunchedEffect(true) {
        viewModel.getGenre()
    }

    var genreList by remember {
        mutableStateOf<List<GenreResponse.GenreResponseItem>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }
    val genreResult by viewModel.genreContent.collectAsState()
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            refreshScope.launch {
                viewModel.getGenre()
                Log.e("4718", "SwipeRefresh ")
            }
        }) {

        when (genreResult) {
            is NetworkResult.Success -> {
                genreList = genreResult.data ?: emptyList()
                Log.e("4718", "GenreScreen Success : ${genreList.size} ")
                Icon(
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .padding(30.dp)
                )
                    FlowRow(
                        maxItemsInEachRow = 2,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 60.dp)
                            .verticalScroll(rememberScrollState())
                    ) {

                        for (item in genreList) {

                            Column (
                                modifier = Modifier.padding(vertical = 10.dp).height(155.dp).fillMaxWidth(0.5f).clickable {
                                }.padding(horizontal = 10.dp)
                            ){
                                Image(
                                    modifier = Modifier.fillMaxWidth().height(130.dp),
                                    painter = rememberAsyncImagePainter(item.imageUrl),
                                    contentDescription = null
                                )
                                Text(text = item.name.toString())
                            }
                        }

                    }


            }

            is NetworkResult.Error -> {
                loading = false
                Log.e("3636", "GenreScreen Error : ${genreResult.message} ")
            }

            is NetworkResult.Loading -> {
                loading = true
            }
        }
    }

}