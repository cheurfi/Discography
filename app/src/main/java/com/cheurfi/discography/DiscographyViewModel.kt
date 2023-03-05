package com.cheurfi.discography

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheurfi.discography.data.Artist
import coroutines.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import network.NetworkInteractor
import javax.inject.Inject

class DiscographyViewModel @Inject constructor(
    private val interactor: NetworkInteractor,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    val artists = MutableStateFlow<List<Artist>>(emptyList())

    fun getArtists(name: String) {
        viewModelScope.launch(dispatcherProvider.computation()) {
            artists.value = interactor.getArtists(name).artists
        }
    }
}