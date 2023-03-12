package com.cheurfi.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheurfi.repository.data.Artist
import com.cheurfi.repository.network.ArtistRepository
import com.cheurfi.utils.coroutines.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val artistRepository: ArtistRepository,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    val searchText = mutableStateOf("")
    val artists = MutableStateFlow<List<Artist>>(emptyList())

    fun getArtists(name: String) {
        viewModelScope.launch(dispatcherProvider.computation()) {
            artists.value = artistRepository.getArtists(name)?.artists ?: emptyList()
        }
    }
}