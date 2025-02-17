package com.platform.mediacenter.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TvChannelItemBox(
    title: String,
    image: Painter,
    onClick: () -> Unit
) {

    Column(modifier = Modifier
        .width(176.dp)
        .height(120.dp)
        .padding(horizontal = 6.dp)
        .clickable {
            onClick()
        }) {
        Card {

            Image(
                modifier = Modifier
                    .width(170.dp)
                    .height(100.dp),
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}