package com.platform.mediacenter.ui.screens.country

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.platform.mediacenter.data.model.country.CountryResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.viewmodel.CountryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CountryScreen(navController: NavHostController,viewModel: CountryViewModel = hiltViewModel()) {
    LaunchedEffect(true) {
        viewModel.getAllCountry()
    }

    var countryList by remember {
        mutableStateOf<List<CountryResponse.CountryResponseItem>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }
    val countryResult by viewModel.countryContent.collectAsState()
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            refreshScope.launch {
                viewModel.getAllCountry()
                Log.e("4718", "SwipeRefresh ")
            }
        }) {

        when (countryResult) {
            is NetworkResult.Success -> {
                countryList = countryResult.data ?: emptyList()
                Log.e("4718", "CountryScreen Success : ${countryList.size} ")
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

                    for (item in countryList) {

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
                Log.e("3636", "CountryScreen Error : ${countryResult.message} ")
            }

            is NetworkResult.Loading -> {
                loading = true
            }
        }
    }

}