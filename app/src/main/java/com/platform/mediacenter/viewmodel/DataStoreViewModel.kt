package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.datastore.DataStoreRepository
import com.platform.mediacenter.utils.Constants.PERSIAN_LANG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    companion object {
        const val LANGUAGE_KEY = "user_language_key"
        const val IS_LOGIN = "is_login"
        const val IS_DARK = "is_dark"
        const val ACCESS_TOKEN = "access_token"
        const val USER_ID = "user_id"
        const val ACTIVE_PLAN = "active_plan"

    }

    fun saveUserLoginStatus(isLogin : Boolean) {
        viewModelScope.launch {
            repository.putBoolean(IS_LOGIN, isLogin)
        }
    }

    fun setDarkTheme(isDark : Boolean) {
        viewModelScope.launch {
            repository.putBoolean(IS_DARK, isDark)
        }
    }

    fun saveUserId(id: String) {
        viewModelScope.launch {
            repository.putString(USER_ID, id)
        }
    }

    fun saveAccessToken(token: String) {
        viewModelScope.launch {
            repository.putString(ACCESS_TOKEN, token)
        }
    }

    fun saveUserLanguage(language: String) {
        viewModelScope.launch {
            repository.putString(LANGUAGE_KEY, language)
        }

    }

    fun geAccessToken(): String = runBlocking {
        repository.getString(ACCESS_TOKEN) ?: ""
    }

    fun getUserLanguage(): String = runBlocking {
        repository.getString(LANGUAGE_KEY) ?: PERSIAN_LANG
    }

    fun getUserId(): String = runBlocking {
        repository.getString(USER_ID) ?: ""
    }

    fun getUserLoginStatus(): Boolean = runBlocking {
        repository.getBoolean(IS_LOGIN) ?: false
    }

    fun getDarkTheme(): Boolean = runBlocking {
        repository.getBoolean(IS_DARK) ?: false
    }

}