package com.platform.mediacenter.ui.screens.profile

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.platform.mediacenter.MainActivity
import com.platform.mediacenter.R
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.ui.components.MyButton
import com.platform.mediacenter.ui.components.MyEditText
import com.platform.mediacenter.ui.theme.colorPrimary
import com.platform.mediacenter.utils.Constants.ACCESS_TOKEN
import com.platform.mediacenter.utils.Constants.USER_ID
import com.platform.mediacenter.viewmodel.DataStoreViewModel
import com.platform.mediacenter.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginChooser(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    Icon(
        imageVector = Icons.Filled.ArrowBackIos,
        contentDescription = null,
        modifier = Modifier
            .clickable {
                navController.popBackStack()
            }
            .padding(30.dp)
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(R.drawable.colorpalette14),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        when (viewModel.screenState) {

            LoginScreenState.LOGIN_STATE -> {
                LoginScreen()
            }

            LoginScreenState.REGISTER_STATE -> {
                SignUpScreen()
            }

            LoginScreenState.VERIFY_CODE -> {
                VerifyCodeScreen()
            }

            LoginScreenState.FORGET_PASS_STATE -> {
                ForgetPassScreen()
            }
        }
    }
}

@Composable
fun LoginScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {
    var isEditable by remember { mutableStateOf(true) }
    var isValid = validateInput(viewModel.inputPhoneState) != "invalid"
    var shouldShowEditText by remember { mutableStateOf(false) }
    var checkUserExist by remember { mutableStateOf("") }
    val activity = LocalContext.current as MainActivity
    val loginResult by viewModel.login.collectAsState()
    val context = LocalContext.current
    LaunchedEffect (true){
        viewModel.toastMessage.collect{ message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyEditText(
            value = viewModel.inputPhoneState,
            onValueChange = { if (isEditable) viewModel.inputPhoneState = it },
            placeholder = "ایمیل یا شماره تماس"
        )

        Spacer(modifier = Modifier.height(15.dp))

        if (shouldShowEditText) {
            MyEditText(
                value = viewModel.passwordTextState,
                onValueChange = { viewModel.passwordTextState = it },
                placeholder = stringResource(R.string.pass)
            )
            isValid = viewModel.passwordTextState.length > 5

        }
        Spacer(modifier = Modifier.height(15.dp))
        LaunchedEffect(Dispatchers.Main) {
            viewModel.checkEmail.collectLatest { response->
                when (response) {
                    is NetworkResult.Success -> {
                        isEditable = false
                        shouldShowEditText = true
                        checkUserExist = response.data?.status.toString()
                        Log.e("4718", "Check User Screen Success : ${response.data?.status} ")

                        viewModel.loading = false
                    }

                    is NetworkResult.Error -> {

                        shouldShowEditText = false
                        viewModel.screenState = LoginScreenState.REGISTER_STATE
                        Log.e("4718", "Check User Screen Error : ${response.message} ")
                        viewModel.loading = false
                    }

                    is NetworkResult.Loading -> {}
                }
            }
        }



        MyButton(
            text = stringResource(R.string.register),
            isValid = isValid,
            isLoading = viewModel.loading
        ) {
            if (checkUserExist == "success") {
                if (validateInput(viewModel.inputPhoneState) == "phone") {
                    viewModel.loginPhone()
                } else if (validateInput(viewModel.inputPhoneState) == "email") {
                    viewModel.loginEmail()
                }

            } else {
                viewModel.checkUserExist()
            }
        }


        when (loginResult) {
            is NetworkResult.Success -> {
                if (loginResult.data?.status == "success") {
                    dataStoreViewModel.saveUserLoginStatus(true)
                    loginResult.data?.userId?.let {
                        USER_ID = it
                        dataStoreViewModel.saveUserId(it)
                    }

                    loginResult.data?.accessToken?.let {
                        dataStoreViewModel.saveAccessToken(it)
                        ACCESS_TOKEN = it
                    }
                    activity.apply {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
                Log.e("4718", "Login Screen Success : ${loginResult.data?.status} ")
                viewModel.loading = false
            }

            is NetworkResult.Error -> {
                Log.e("4718", "Login Screen Error : ${loginResult.message} ")
                viewModel.loading = false
            }

            is NetworkResult.Loading -> {
//                viewModel.loading = true
            }
        }

        TextButton({
            viewModel.screenState = LoginScreenState.FORGET_PASS_STATE
        }) {
            Text(
                text = stringResource(R.string.forget_pass),
                color = colorPrimary,
                style = MaterialTheme.typography.h6
            )
        }
    }
}


fun validateInput(input: String): String {
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
    val phoneRegex = "^09\\d{9}$".toRegex()

    return when {
        emailRegex.matches(input) -> "email"
        phoneRegex.matches(input) -> "phone"
        else -> "invalid"
    }
}
