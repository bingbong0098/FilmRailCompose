package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.genre.GenreResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val repository: GenreRepository) : ViewModel() {

    val genreContent = MutableStateFlow<NetworkResult<GenreResponse>>(NetworkResult.Loading())


    fun getGenre() {
        viewModelScope.launch {
            genreContent.emit(repository.getGenre())

        }
    }

}