package com.platform.mediacenter.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.platform.mediacenter.ui.theme.colorPrimary


@Composable
fun ContinueWatchingItemBox(image: Painter, progress: Float, title: String, onClick: () -> Unit) {

    Card(modifier = Modifier
        .padding(5.dp)
        .width(154.dp)
        .height(224.dp)
        ) {
        Column {
            Image(
                contentScale = ContentScale.Crop,
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            LinearProgressIndicator(progress, Modifier.fillMaxWidth(), color = colorPrimary)

            Text(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp), textAlign = TextAlign.End, text = title, style = MaterialTheme.typography.h6, fontSize = 12.sp)
        }
    }

}