package com.platform.mediacenter.ui.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.platform.mediacenter.ui.theme.drawerBgColor
import com.platform.mediacenter.ui.theme.drawerTopColor
import com.platform.mediacenter.MainActivity
import com.platform.mediacenter.R
import com.platform.mediacenter.navigation.Screen
import com.platform.mediacenter.ui.theme.LocalSpacing
import com.platform.mediacenter.ui.theme.MediaCenterTheme
import com.platform.mediacenter.ui.theme.colorPrimary
import com.platform.mediacenter.utils.Constants.ACCESS_TOKEN
import com.platform.mediacenter.utils.Constants.ENGLISH_LANG
import com.platform.mediacenter.utils.Constants.PERSIAN_LANG
import com.platform.mediacenter.utils.Constants.USER_ID
import com.platform.mediacenter.viewmodel.DataStoreViewModel

@Composable
fun DrawerContent(
    navController: NavController,
    viewModel: DataStoreViewModel = hiltViewModel(),
    isDarkTheme: MutableState<Boolean>,
    onMenuClick: () -> Unit,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.drawerBgColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.drawerTopColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(70.dp)
                    .height(70.dp),
                tint = Color.White,
                painter = painterResource(R.drawable.colorpalette07),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = stringResource(R.string.label),
                style = MaterialTheme.typography.h4,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier.padding(vertical = LocalSpacing.current.semiMedium)
        ) {
            val activity = LocalContext.current as MainActivity
            val isLogin = dataStoreViewModel.getUserLoginStatus()
            DrawerItems(
                listOf(
                    stringResource(R.string.home),
                    stringResource(R.string.genre),
                    stringResource(R.string.country),
                    stringResource(R.string.profile),
                    stringResource(R.string.subscription),
                    stringResource(R.string.devices),
                    stringResource(R.string.downloads),
                    stringResource(R.string.settings),
                    if (isLogin) stringResource(R.string.exit) else stringResource(R.string.login_register),
                ),
                listOf(
                    painterResource(R.drawable.icon_home),
                    painterResource(R.drawable.outline_folder_24),
                    painterResource(R.drawable.outline_outlined_flag_24),
                    painterResource(R.drawable.outline_person_24),
                    painterResource(R.drawable.ic_subscriptions_black_24dp),
                    painterResource(R.drawable.devices7),
                    painterResource(R.drawable.ic_file_download_black_24dp),
                    painterResource(R.drawable.outline_settings_24),
                    painterResource(R.drawable.outline_lock_24),
                ),
                isLogin,
                navController,
                onMenuClick
            )
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp), onClick = {
                viewModel.saveUserLanguage(PERSIAN_LANG)
                activity.apply {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }) {
                Text("persian")
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                viewModel.saveUserLanguage(ENGLISH_LANG)
                activity.apply {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }) {
                Text("english")
            }

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(vertical = 5.dp)
            )

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                isDarkTheme.value = !isDarkTheme.value
                dataStoreViewModel.setDarkTheme(isDarkTheme.value)
            }) {
                Text("theme")
            }
        }
    }
}

@Composable
fun DrawerItems(
    titles: List<String>,
    icons: List<Painter>,
    isLogin: Boolean,
    navController: NavController,
    onMenuClick: () -> Unit
) {
    val loginRegister = stringResource(R.string.login_register)
    val home = stringResource(R.string.home)
    val genre = stringResource(R.string.genre)
    val country = stringResource(R.string.country)
    val profile = stringResource(R.string.profile)
    val devices = stringResource(R.string.devices)
    val settings = stringResource(R.string.settings)
    val subscription = stringResource(R.string.subscription)
    val downloads = stringResource(R.string.downloads)
    val exit = stringResource(R.string.exit)
    val skipIndexes = setOf(3, 4, 5, 6)

    ExitAlertDialog()
    titles.forEachIndexed { index, title ->
        if (isLogin.not() && index in skipIndexes) return@forEachIndexed

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onMenuClick()
                    when (title) {
                        loginRegister -> {
                            navController.navigate(Screen.StartLogin.route)
                        }

                        home -> {
                            navController.navigate(Screen.Home.route)
                        }

                        genre -> {
                            navController.navigate(Screen.Genre.route)
                        }

                        country -> {
                            navController.navigate(Screen.Country.route)
                        }

                        profile -> {
                            navController.navigate(Screen.Profile.route)
                        }

                        downloads -> {
//                            navController.navigate(Screen.Profile.route)
                        }

                        subscription -> {
                            navController.navigate(Screen.PurchasePlan.route)
                        }

                        devices -> {
                            navController.navigate(Screen.Devices.route)
                        }

                        settings -> {
//                            navController.navigate(Screen.Profile.route)
                        }

                        exit -> {
                            openDialogState.value = true
                        }

                    }
                }
                .padding(
                    horizontal = LocalSpacing.current.small,
                    vertical = LocalSpacing.current.semiMedium
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = LocalSpacing.current.medium)
                    .width(22.dp)
                    .height(22.dp),
                painter = icons[index],
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(horizontal = LocalSpacing.current.medium),
                text = title,
                fontSize = 14.sp,
                style = MaterialTheme.typography.h5,
            )
        }
    }
}

val openDialogState = mutableStateOf(false)
@Composable
fun ExitAlertDialog(
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()) {
    val openDialog = remember { openDialogState }
    val activity = LocalContext.current as MainActivity
    if (openDialog.value){
        AlertDialog(
            backgroundColor = Color(0xFFF3F3F3),
            onDismissRequest = {
                openDialog.value = false
            },
            text = { Text("مطمئناً از سیستم خارج می شوید؟", style = MaterialTheme.typography.h5 ) },
            confirmButton = {

                TextButton({
                    openDialog.value = false
                    dataStoreViewModel.saveUserLoginStatus(false)
                    dataStoreViewModel.saveUserId("")
                    dataStoreViewModel.saveAccessToken("")
                    ACCESS_TOKEN = dataStoreViewModel.geAccessToken()
                    USER_ID = dataStoreViewModel.getUserId()
                    activity.apply {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }) {

                    Text("بله", color = colorPrimary, style = MaterialTheme.typography.h5)
                }
            },
            dismissButton = {
                TextButton({
                    openDialog.value = false
                }) {
                    Text("لغو", color = colorPrimary, style = MaterialTheme.typography.h5)
                }
            }
        )

    }
}