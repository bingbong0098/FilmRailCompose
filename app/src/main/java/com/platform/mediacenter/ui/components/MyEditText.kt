package com.platform.mediacenter.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyEditText(
    value :String,
    onValueChange : (item : String) -> Unit,
    placeholder : String
){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp)),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.h6,
                fontSize = 16.sp
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(),
        singleLine = true
    )
}