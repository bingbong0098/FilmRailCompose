package com.platform.mediacenter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.BaseResponse
import com.platform.mediacenter.data.model.login.UserResponse
import com.platform.mediacenter.data.model.login.VerifyCodeResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.ProfileRepository
import com.platform.mediacenter.ui.screens.profile.LoginScreenState
import com.platform.mediacenter.utils.Constants.USER_AGENT
import com.platform.mediacenter.utils.Constants.USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {
    //Shared ViewModel
    var screenState by mutableStateOf(LoginScreenState.LOGIN_STATE)
    var inputPhoneState by mutableStateOf("")
    var passwordTextState by mutableStateOf("")
    var nameTextState by mutableStateOf("")
    var loading by mutableStateOf(false)

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String> = _toastMessage.asSharedFlow()

    val resetPassword = MutableStateFlow<NetworkResult<BaseResponse?>>(NetworkResult.Loading())
    val checkEmail = MutableStateFlow<NetworkResult<BaseResponse?>>(NetworkResult.Loading())
    val login = MutableStateFlow<NetworkResult<UserResponse?>>(NetworkResult.Loading())
    val signUp = MutableStateFlow<NetworkResult<UserResponse?>>(NetworkResult.Loading())
    val verifyCode = MutableStateFlow<NetworkResult<VerifyCodeResponse?>>(NetworkResult.Loading())

    val userProfileData = MutableStateFlow<NetworkResult<UserResponse?>>(NetworkResult.Loading())
    val isLoginValid = MutableStateFlow<String?>("valid")

    fun updateProfile(
        name: RequestBody?,
        email: RequestBody?,
        phone: RequestBody?,
        password: RequestBody?,
        currentPassword: RequestBody?,
        photo: MultipartBody.Part?,
        gender: RequestBody?,
        userId: RequestBody?,
    ) {
        loading = true
        viewModelScope.launch {
            repository.updateProfile(userId,name, email, phone, password, currentPassword, photo, gender)
        }
    }

    fun getUserData() {
        loading = true
        viewModelScope.launch {
            userProfileData.emit(repository.getUserData(USER_ID))
        }
    }

    fun checkLoginValid() {
        viewModelScope.launch {
            val response = repository.checkLoginValid()
            if (response.isSuccessful){
                if (response.body()?.status == "success"){
                    isLoginValid.emit("valid")
                }else{
                    isLoginValid.emit("invalid")
                }
            }else if (response.code() == 401){
                isLoginValid.emit("401")
            }

        }
    }

    fun deactivateAccount(password: String?, reason: String?) {
        loading = true
        viewModelScope.launch {
            repository.deactivateAccount(USER_ID, password, reason)
        }
    }

    fun resetPasswordPhone(verifyCode: String) {
        loading = true
        viewModelScope.launch {
            resetPassword.emit(repository.resetPasswordPhone(inputPhoneState, verifyCode))
        }
    }

    fun resetPassword() {
        loading = true
        viewModelScope.launch {
            resetPassword.emit(repository.resetPassword(inputPhoneState))
        }
    }

    fun checkUserExist() {
        loading = true
        viewModelScope.launch {
            checkEmail.emit(repository.checkUserExistOrNot(inputPhoneState))
        }
    }

    fun loginPhone() {
        loading = true
        viewModelScope.launch {
            val response =
                repository.loginPhone(inputPhoneState, USER_AGENT, passwordTextState).data?.data
            login.emit(repository.loginPhone(inputPhoneState, USER_AGENT, passwordTextState))
            response?.let { _toastMessage.emit(it) }
        }
    }

    fun loginEmail() {
        loading = true
        viewModelScope.launch {
            login.emit(repository.loginEmail(inputPhoneState, USER_AGENT, passwordTextState))
        }
    }

    fun signUpEmail() {
        loading = true
        viewModelScope.launch {
            signUp.emit(repository.signUpEmail(inputPhoneState, USER_AGENT, passwordTextState, ""))
        }
    }

    fun signUpPhone(verifyCode: String) {
        loading = true
        viewModelScope.launch {
            signUp.emit(
                repository.signUpPhone(
                    inputPhoneState,
                    USER_AGENT,
                    passwordTextState,
                    nameTextState,
                    verifyCode
                )
            )
        }
    }

    fun verifyCode() {
        loading = true
        viewModelScope.launch {
            verifyCode.emit(repository.verifyCode(inputPhoneState))
        }
    }

}