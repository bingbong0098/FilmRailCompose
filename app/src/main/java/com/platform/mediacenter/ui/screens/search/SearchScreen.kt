package com.platform.mediacenter.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.platform.mediacenter.R
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.navigation.Screen
import com.platform.mediacenter.ui.screens.movie.MovieItemBox
import com.platform.mediacenter.ui.theme.colorPrimary
import com.platform.mediacenter.viewmodel.ProfileViewModel
import com.platform.mediacenter.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(navController: NavHostController, viewModel: SearchViewModel = hiltViewModel()) {
    val searchResult by viewModel.searchContent.collectAsState()
    var searchTextState by remember { mutableStateOf("") }
    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContent = {

            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp, top = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .padding(horizontal = 85.dp)
                        .background(Color.Gray)
                )
                Text(
                    "فیلتر بر اساس :",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(8.dp)
                )

                var movieButtonState by remember { mutableStateOf(true) }
                var seriesButtonState by remember { mutableStateOf(false) }
                var tvButtonState by remember { mutableStateOf(true) }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (movieButtonState) colorPrimary else Color.Gray)
                            .clickable {
                                movieButtonState = !movieButtonState
                            }) {
                        Text(
                            modifier = Modifier.padding(vertical = 14.dp, horizontal = 20.dp),
                            text = "فیلم ها",
                            style = MaterialTheme.typography.h4,
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (seriesButtonState) colorPrimary else Color.Gray)
                            .clickable {
                                seriesButtonState = !seriesButtonState
                            }) {
                        Text(
                            modifier = Modifier.padding(vertical = 14.dp, horizontal = 20.dp),
                            text = "سریال ها",
                            style = MaterialTheme.typography.h4,
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (tvButtonState) colorPrimary else Color.Gray)
                            .clickable {
                                tvButtonState = !tvButtonState
                            }) {
                        Text(
                            modifier = Modifier.padding(vertical = 14.dp, horizontal = 20.dp),
                            text = "تلویزیون ها",
                            style = MaterialTheme.typography.h4,
                            color = Color.White
                        )
                    }
                }
                if (tvButtonState) {
                    Text(
                        "دسته بندی تلویزیون",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(CircleShape)
                            .clickable {

                            }
                            .background(colorPrimary)
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            "دسته بندی ها",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = Color.White
                        )

                        Icon(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                }

                if (movieButtonState || seriesButtonState) {
                    Text(
                        "ژانر",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(CircleShape)
                            .clickable {

                            }
                            .background(colorPrimary)
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            "همه ژانر ها",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = Color.White
                        )

                        Icon(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Text(
                        "کشور",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(CircleShape)
                            .clickable {

                            }
                            .background(colorPrimary)
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            "همه کشور ها",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = Color.White
                        )

                        Icon(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }



                    Text(
                        "محدوده سال",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(8.dp)
                    )

                    var sliderPositionYear by remember { mutableStateOf(0f..125f) }
                    RangeSlider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        value = sliderPositionYear,
                        onValueChange = { newRange ->
                            sliderPositionYear = newRange
                        },
                        valueRange = 0f..125f, // محدوده مجاز برای اسلایدر
                        steps = 125 // تعداد تقسیمات بین مقدار کم و زیاد
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "2025",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = Color.Black
                        )
                        Text(
                            "1990",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = Color.Black
                        )
                    }
                    Text(
                        "امتیاز IMDB",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(8.dp)
                    )
                    var sliderPosition by remember { mutableStateOf(0f..10f) }
                    RangeSlider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        value = sliderPosition,
                        onValueChange = { newRange ->
                            sliderPosition = newRange
                        },
                        valueRange = 0f..10f, // محدوده مجاز برای اسلایدر
                        steps = 10 // تعداد تقسیمات بین مقدار کم و زیاد
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "10",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = Color.Black
                        )
                        Text(
                            "1",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.size(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {

                        TextButton({

                        }) {
                            Text(
                                "لغو",
                                style = MaterialTheme.typography.h3,
                                modifier = Modifier.padding(horizontal = 12.dp),
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.size(5.dp))

                        TextButton({
                            viewModel.getSearchData(
                                query = searchTextState,
                                type = "movietvserieslive",
                                rangeFrom = 2000,
                                rangeTo = 2022,
                                rangeimdbfrom = 1,
                                rangeimdbto = "9.9",
                                countryId = 0,
                                tvCategoryId = 0,
                                genreId = 0
                            )
                        }) {
                            Text(
                                "جستجو",
                                style = MaterialTheme.typography.h3,
                                modifier = Modifier.padding(horizontal = 12.dp),
                                color = Color.Black
                            )
                        }
                    }

                }

            }
        }
    ) {
        if (searchResult is NetworkResult.Success) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 95.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(searchResult.data?.movie ?: emptyList()){ searchData ->
                    MovieItemBox(
                        height = 225,
                        imageHeight = 180,
                        image = rememberAsyncImagePainter(searchData?.posterUrl),
                        name = searchData?.title!!,
                        year = "",
                        quality = "",
                        imdbRate = "",
                        videoDescadd = ""
                    ){
                        val actionType = if(searchData.isTvseries == "1") "tvseries" else "movie"
                        navController.navigate(
                            Screen.Detail.withArg(
                                searchData.videosId!!,actionType
                            )
                        )
                    }
                }
            }

        }else{
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 40.dp),
                    painter = painterResource(R.drawable.bg_no_item_citynew),
                    contentDescription = null
                )
            }
        }

        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = {
                    Text(text = "جستجو", color = Color.Gray)
                },
                value = searchTextState,
                onValueChange = {
                    searchTextState = it
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                },
                maxLines = 1,
                singleLine = true)
        }
    }


}