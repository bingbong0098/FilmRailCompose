package com.platform.mediacenter.ui.screens.tv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.platform.mediacenter.data.model.LiveTvResponse
import com.platform.mediacenter.ui.screens.home.TvChannelItemBox


@Composable
fun LiveTvCategoryItemBox(title: String, list: List<LiveTvResponse.LiveTvResponseItem.Channel?>) {
    Column (modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.h6,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow(modifier = Modifier.padding(vertical = 4.dp)) {
            itemsIndexed(list) { index, item ->

//                val video = item?.videos?.get(index)
                val imageUrl = item?.posterUrl ?: ""
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

                TvChannelItemBox(
                    image = painter,
                    title = list[index]?.tvName.toString()
                ) {

//                    navController.navigate(Screen.Detail.route)
                }

            }
        }
    }
}