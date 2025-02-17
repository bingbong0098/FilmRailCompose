package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import com.platform.mediacenter.data.repository.ContinueWatchingRepository
import javax.inject.Inject

class ContinueWatchingViewModel @Inject constructor(private val repository: ContinueWatchingRepository) : ViewModel() {

}