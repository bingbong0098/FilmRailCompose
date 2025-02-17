package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.country.CountryResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val repository: CountryRepository) : ViewModel() {

    val countryContent = MutableStateFlow<NetworkResult<CountryResponse>>(NetworkResult.Loading())


    fun getAllCountry() {
        viewModelScope.launch {
            countryContent.emit(repository.getAllCountry())

        }
    }

}