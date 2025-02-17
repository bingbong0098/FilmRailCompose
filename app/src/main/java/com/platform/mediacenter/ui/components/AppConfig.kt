package com.platform.mediacenter.ui.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.platform.mediacenter.utils.Constants.ACCESS_TOKEN
import com.platform.mediacenter.utils.Constants.IS_DARK_THEME
import com.platform.mediacenter.utils.Constants.USER_ID
import com.platform.mediacenter.utils.Constants.USER_LANGUAGE
import com.platform.mediacenter.viewmodel.DataStoreViewModel

@Composable
fun AppConfig(
    viewModel: DataStoreViewModel = hiltViewModel()
) {
    getDataStoreVariables(viewModel)

}

private fun getDataStoreVariables(viewModel: DataStoreViewModel) {
    IS_DARK_THEME = viewModel.getDarkTheme()
    USER_LANGUAGE = viewModel.getUserLanguage()
    ACCESS_TOKEN = viewModel.geAccessToken()
    USER_ID = viewModel.getUserId()
    viewModel.saveUserLanguage(USER_LANGUAGE)
    viewModel.setDarkTheme(IS_DARK_THEME)

}