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
import com.platform.mediacenter.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ForgetPassScreen(viewModel: ProfileViewModel = hiltViewModel()) {

    var isEditable by remember { mutableStateOf(true) }
    var verifyCodeTextState by remember { mutableStateOf("") }
    val activity = LocalContext.current as MainActivity
    val verifyCodeResult by viewModel.verifyCode.collectAsState()
    var sendVerifyCode by remember { mutableStateOf("") }
    val isValid = validateInput(viewModel.inputPhoneState) != "invalid"

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

        Spacer(modifier = Modifier.height(15.dp))

        MyEditText(
            value = viewModel.inputPhoneState,
            onValueChange = { if (isEditable) viewModel.inputPhoneState = it },
            placeholder = stringResource(R.string.enter_email_phone)
        )


        Spacer(modifier = Modifier.height(10.dp))
        when (verifyCodeResult) {
            is NetworkResult.Success -> {
                sendVerifyCode = verifyCodeResult.data?.status.toString()
                Log.e("4718", "verifyCode API Success : ${verifyCodeResult.data?.status} ")
                if (verifyCodeResult.data?.status == "success") {
                    isEditable = false
                    MyEditText(
                        value = verifyCodeTextState,
                        onValueChange = { verifyCodeTextState = it },
                        placeholder = stringResource(R.string.verify_code)
                    )
                } else {
                    Toast.makeText(activity, verifyCodeResult.data?.status, Toast.LENGTH_SHORT)
                        .show()
                }
                viewModel.loading = false
            }

            is NetworkResult.Error -> {
                Log.e("4718", "verifyCode API Error : ${verifyCodeResult.message} ")
                viewModel.loading = false
            }

            is NetworkResult.Loading -> {}
        }
        Spacer(modifier = Modifier.height(15.dp))
        MyButton(
            text = stringResource(R.string.register),
            isValid = isValid,
            isLoading = viewModel.loading
        ) {
            if (validateInput(viewModel.inputPhoneState) == "email") {
                viewModel.resetPassword()
            } else if (validateInput(viewModel.inputPhoneState) == "phone") {
                if (sendVerifyCode == "success") {
                    viewModel.resetPasswordPhone(verifyCodeTextState)
                } else {
                    viewModel.verifyCode()
                }
            }
        }
    }

    LaunchedEffect(Dispatchers.Main) {
        viewModel.resetPassword.collectLatest { resetPasswordResult ->
            when (resetPasswordResult) {
                is NetworkResult.Success -> {

                    Log.e(
                        "4718",
                        "Reset Password API Success : ${resetPasswordResult.data?.status} "
                    )
                    if (resetPasswordResult.data?.status == "success") {
                        activity.apply {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        Toast.makeText(
                            activity,
                            resetPasswordResult.data.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    } else {
                        Toast.makeText(
                            activity,
                            resetPasswordResult.data?.status,
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    }

                    viewModel.loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("4718", "Reset Password API Error : ${resetPasswordResult.message} ")
                    viewModel.loading = false
                }

                is NetworkResult.Loading -> {}
            }
        }
    }

}