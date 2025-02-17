package com.platform.mediacenter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun HomeShimmerLoading() {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        // Shimmer برای اسلایدر
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(12.dp))
                .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Shimmer برای لیست بازیگران محبوب
        Text("ستارگان محبوب", style = MaterialTheme.typography.h6, modifier = Modifier.padding(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(4) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(10.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Shimmer برای کانال‌های ویژه
        Text("کانال های ویژه", style = MaterialTheme.typography.h6, modifier = Modifier.padding(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .size(100.dp, 60.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Shimmer برای آخرین فیلم‌ها
        Text("آخرین فیلم ها", style = MaterialTheme.typography.h6, modifier = Modifier.padding(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(2) {
                Column {
                    Box(
                        modifier = Modifier
                            .size(100.dp, 150.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(12.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
                    )
                }
            }
        }
    }
}
