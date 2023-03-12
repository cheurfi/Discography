package com.cheurfi.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheurfi.search.data.Artist
import com.cheurfi.search.network.NetworkInteractor
import com.cheurfi.utils.coroutines.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val interactor: NetworkInteractor,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    val artists = MutableStateFlow<List<Artist>>(emptyList())

    fun getArtists(name: String) {
        viewModelScope.launch(dispatcherProvider.computation()) {
            artists.value = interactor.getArtists(name)?.artists ?: emptyList()
        }
    }
}