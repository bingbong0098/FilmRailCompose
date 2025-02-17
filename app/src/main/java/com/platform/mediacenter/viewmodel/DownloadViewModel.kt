package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.download.DownloadEntity
import com.platform.mediacenter.data.repository.DownloadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(private val repository: DownloadRepository):ViewModel() {

    fun insertDownloadItem(item : DownloadEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDownloadItem(item)
        }
    }
}