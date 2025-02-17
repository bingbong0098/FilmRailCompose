package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.comment.CommentsResponse
import com.platform.mediacenter.data.model.detail.SingleDetailsResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.DetailRepository
import com.platform.mediacenter.utils.Constants.USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    val comments = MutableStateFlow<NetworkResult<List<CommentsResponse?>?>>(NetworkResult.Loading())
    val singleDetails = MutableStateFlow<NetworkResult<SingleDetailsResponse?>>(NetworkResult.Loading())
    private val _isLiked = MutableStateFlow(false)
    val isLiked: StateFlow<Boolean> = _isLiked.asStateFlow()

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String> = _toastMessage.asSharedFlow()

    fun getSingleDetails(type: String?, id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            singleDetails.emit(repository.getSingleDetails(type, id))
        }
    }

    fun getAllComments(videoId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            comments.emit(repository.getAllComments(videoId))
        }
    }

    fun getFavoriteStatus(videoId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getFavoriteStatus(USER_ID, videoId).data?.status == "success"
            _isLiked.value = result
        }
    }

    fun toggleLike(videoId: String?) {

        viewModelScope.launch {
            if (_isLiked.value) {
                val response = repository.removeFavorite(USER_ID,videoId)
                if (response.isSuccessful) {
                    _isLiked.value = false
                    response.body()!!.message?.let { _toastMessage.emit(it) }
                }
            } else {
                val response = repository.addToFavorite(USER_ID,videoId)
                if (response.isSuccessful) {
                    _isLiked.value = true
                    response.body()!!.message?.let { _toastMessage.emit(it) }
                }
            }
        }
    }

}