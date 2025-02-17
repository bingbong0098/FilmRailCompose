package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.platform.mediacenter.data.model.movie.MovieResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.ContinueWatchingRepository
import com.platform.mediacenter.data.repository.MovieRepository
import com.platform.mediacenter.data.source.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository,private val continueWatchingrepository: ContinueWatchingRepository) : ViewModel() {
    val moviesList = Pager(PagingConfig(1)){
        MoviesPagingSource(movieRepository)
    }.flow.cachedIn(viewModelScope)

    val movieContent = MutableStateFlow<NetworkResult<MovieResponse>>(NetworkResult.Loading())


    fun getMoviesContent(page : Int) {
        viewModelScope.launch {
            movieContent.emit(movieRepository.getMovies(page))
        }
    }

//    fun loadData(type: MoreItemType)

}