package com.platform.mediacenter.ui.screens.tv

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.platform.mediacenter.data.model.LiveTvResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.viewmodel.LiveTvViewModel

@Composable
fun LiveTvScreen(navController: NavController,viewModel: LiveTvViewModel = hiltViewModel()){

    LaunchedEffect(true) {
        viewModel.getLiveTvContent()
    }
    var tvChannelsList by remember {
        mutableStateOf<List<LiveTvResponse.LiveTvResponseItem?>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val tvChannelsResult by viewModel.liveTvContent.collectAsState()

    when (tvChannelsResult) {
        is NetworkResult.Success -> {


            tvChannelsList = tvChannelsResult.data ?: emptyList()
            Log.e("4718", "LiveTvScreen Success : ${tvChannelsList.size} ")

            LazyColumn (modifier = Modifier.fillMaxSize().padding(bottom = 55.dp)) {
                itemsIndexed(tvChannelsList){ index, item ->

//                    Text("qeqweqew")
                    LiveTvCategoryItemBox(title = tvChannelsList[index]?.title!!, list = tvChannelsList[index]?.channels!! )
                }
            }

            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("4718", "LiveTvScreen Error : ${tvChannelsResult.message} ")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }
}
