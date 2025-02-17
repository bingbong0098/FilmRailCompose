package com.platform.mediacenter.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.platform.mediacenter.R


@Composable
fun Loading3Dots(isDark: Boolean) {
    if (isDark){
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading3dotsdark))
        LottieAnimation(composition, iterations = LottieConstants.IterateForever)
    }else{
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading3dots))
        LottieAnimation(composition, iterations = LottieConstants.IterateForever)

    }
}