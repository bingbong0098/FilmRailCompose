package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.Movie
import com.platform.mediacenter.data.model.movie.MovieResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.FavouriteRepository
import com.platform.mediacenter.utils.Constants.USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel  @Inject constructor(private val repository: FavouriteRepository) : ViewModel() {
    val favoriteList = MutableStateFlow<NetworkResult<List<Movie>?>>(NetworkResult.Loading())


    suspend fun getFavoriteList(page : Int) {
        viewModelScope.launch {
            favoriteList.emit(repository.getFavoriteList(USER_ID,page))

        }
    }

}