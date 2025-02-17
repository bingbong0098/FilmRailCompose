package com.platform.mediacenter.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.platform.mediacenter.ui.theme.LocalShape
import com.platform.mediacenter.ui.theme.colorPrimary

@Composable
fun MyButton(
    text: String,
    isValid: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
        .fillMaxWidth()
        .height(45.dp)
        .padding(horizontal = 50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isValid) colorPrimary else Color.Gray,
            contentColor = Color.White
        ),
        shape = LocalShape.current.biggerSmall,
        onClick = {
            if (isValid) onClick()
        }) {
        if (isLoading) {
            Loading3Dots(false)
        } else {
            Text(
                text = text,
                fontSize = 16.sp,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(horizontal = 33.dp)
            )
        }
    }
}