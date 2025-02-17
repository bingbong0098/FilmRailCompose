package com.platform.mediacenter.ui.screens.blog

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.NestedScrollView
import com.platform.mediacenter.utils.Constants.BLOG_URL

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BlogScreen() {


    AndroidView(
        factory = { context ->
            NestedScrollView(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                val webView = WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    settings.userAgentString = System.getProperty("http.agent")
                    loadUrl(BLOG_URL)
                }
                addView(webView)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
