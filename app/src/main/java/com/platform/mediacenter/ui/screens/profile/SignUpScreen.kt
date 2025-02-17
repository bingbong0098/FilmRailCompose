package com.platform.mediacenter.ui.screens.profile

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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
fun SignUpScreen(viewModel: ProfileViewModel = hiltViewModel(), dataStoreViewModel: DataStoreViewModel = hiltViewModel()) {
    val activity = LocalContext.current as MainActivity
    val isValid = viewModel.passwordTextState.length > 5 && viewModel.nameTextState.isNotEmpty()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = stringResource(R.string.enter_email_phone),
            color = colorPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4
        )
        MyEditText(
            value = viewModel.inputPhoneState,
            onValueChange = { },
            placeholder = "ایمیل یا شماره تماس"
        )
        Spacer(modifier = Modifier.height(10.dp))
        MyEditText(
            value = viewModel.nameTextState,
            onValueChange = { viewModel.nameTextState = it },
            placeholder = stringResource(R.string.name)
        )
        Spacer(modifier = Modifier.height(10.dp))
        MyEditText(
            value = viewModel.passwordTextState,
            onValueChange = { viewModel.passwordTextState = it },
            placeholder = stringResource(R.string.pass)
        )

        LaunchedEffect(Dispatchers.Main) {
            viewModel.verifyCode.collectLatest { verifyCodeResult->
                when (verifyCodeResult) {
                    is NetworkResult.Success -> {
                        Log.e("4718", "VerifyCode API Success : ${verifyCodeResult.data?.status} ")
                        viewModel.loading = false
                        viewModel.screenState = LoginScreenState.VERIFY_CODE
                    }

                    is NetworkResult.Error -> {
                        Log.e("4718", "VerifyCode API Error : ${verifyCodeResult.message} ")
                        viewModel.loading = false
                    }

                    is NetworkResult.Loading -> {
                    }
                }

            }

        }
        Spacer(modifier = Modifier.height(15.dp))
        MyButton(text = stringResource(R.string.register), isValid = isValid, isLoading = viewModel.loading) {

            if (validateInput(viewModel.inputPhoneState) == "email") {
                viewModel.signUpEmail()
            } else if (validateInput(viewModel.inputPhoneState) == "phone") {
                viewModel.verifyCode()
            }
        }

        LaunchedEffect(Dispatchers.Main) {
            viewModel.signUp.collectLatest {signUpPhoneResult->
                when (signUpPhoneResult) {
                    is NetworkResult.Success -> {

                        Log.e("4718", "signUpPhone API Success : ${signUpPhoneResult.data?.status} ")
                        if (signUpPhoneResult.data?.status == "success") {
                            dataStoreViewModel.saveUserLoginStatus(true)
                            signUpPhoneResult.data.userId?.let {
                                USER_ID = it
                                dataStoreViewModel.saveUserId(it)
                            }

                            signUpPhoneResult.data.accessToken?.let {
                                dataStoreViewModel.saveAccessToken(it)
                                ACCESS_TOKEN = it
                            }
                            activity.apply {
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                        } else {
                            Toast.makeText(activity, signUpPhoneResult.data?.data, Toast.LENGTH_SHORT).show()
                        }

                        viewModel.loading = false
                    }

                    is NetworkResult.Error -> {
                        Log.e("4718", "signUpPhone API Error : ${signUpPhoneResult.message} ")
                        viewModel.loading = false
                    }
                    is NetworkResult.Loading -> {}
                }
            }
        }
    }
}

@Composable
fun VerifyCodeScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {

    val activity = LocalContext.current as MainActivity
    var verifyCodeTextState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "کد تایید را وارد کنید",
            color = colorPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4
        )

        Spacer(modifier = Modifier.height(15.dp))

        MyEditText(
            value = verifyCodeTextState,
            onValueChange = { verifyCodeTextState = it },
            placeholder = stringResource(R.string.verify_code)
        )

        Spacer(modifier = Modifier.height(15.dp))

        MyButton(text = stringResource(R.string.register), isValid = true, isLoading = viewModel.loading) {
            viewModel.signUpPhone(verifyCodeTextState)
        }
        TextButton({
            viewModel.verifyCode()
        }) {
            Text(text = "ارسال دوباره کد تایید")
        }
    }

    LaunchedEffect(Dispatchers.Main) {
        viewModel.signUp.collectLatest {signUpPhoneResult->
            when (signUpPhoneResult) {
                is NetworkResult.Success -> {

                    Log.e("4718", "signUpPhone API Success : ${signUpPhoneResult.data?.status} ")
                    if (signUpPhoneResult.data?.status == "success") {
                        dataStoreViewModel.saveUserLoginStatus(true)
                        signUpPhoneResult.data.userId?.let {
                            USER_ID = it
                            dataStoreViewModel.saveUserId(it)
                        }

                        signUpPhoneResult.data.accessToken?.let {
                            dataStoreViewModel.saveAccessToken(it)
                            ACCESS_TOKEN = it
                        }
                        activity.apply {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    } else {
                        Toast.makeText(activity, signUpPhoneResult.data?.data, Toast.LENGTH_SHORT).show()
                    }
                    viewModel.loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("4718", "signUpPhone API Error : ${signUpPhoneResult.message} ")
                    viewModel.loading = false
                }

                is NetworkResult.Loading -> {}
            }
        }
    }
}