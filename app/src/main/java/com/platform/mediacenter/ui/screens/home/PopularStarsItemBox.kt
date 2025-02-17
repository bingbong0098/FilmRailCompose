package com.platform.mediacenter.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.platform.mediacenter.ui.theme.LocalSpacing

@Composable
fun PopularStarsItemBox(
    title: String,
    image: Painter,
    onClick: () -> Unit
) {


    Card(
        modifier = Modifier.padding(horizontal = 5.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .width(90.dp)
                .height(130.dp)
                .clickable {
                    onClick()
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(75.dp)
                    .width(75.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = LocalSpacing.current.semiMedium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }

}