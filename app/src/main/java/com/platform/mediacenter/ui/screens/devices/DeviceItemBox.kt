package com.platform.mediacenter.ui.screens.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.platform.mediacenter.R

@Composable
fun DeviceItemBox(name: String) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 30.dp, vertical = 10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.Gray
                )
                .padding(start = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {
                Image(
                    modifier = Modifier.padding(9.dp),
                    painter = painterResource(R.drawable.devices7),
                    contentDescription = null
                )

            }
            Text(
                text = name,
                modifier = Modifier
                    .padding(vertical = 25.dp, horizontal = 8.dp)
                    .weight(0.1f)
                    .padding(horizontal = 5.dp),
                maxLines = 1
            )


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {

                }
            ) {
                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .width(7.dp)
                        .height(50.dp)
                        .padding(3.dp)
                )

                Icon(
                    modifier = Modifier.padding(horizontal = 10.dp).height(25.dp).clickable {

                    },
                    tint = Color.Red,
                    imageVector = Icons.Filled.RestoreFromTrash,
                    contentDescription = null
                )

            }

        }
    }
}


@Composable
@Preview
fun Preview() {
    DeviceItemBox("hhhhhh hjgjhg jhghjg jhgjhg j ghj ghj ghjjj j hhhhh")
}