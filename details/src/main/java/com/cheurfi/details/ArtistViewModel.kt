package com.cheurfi.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheurfi.repository.data.Recording
import com.cheurfi.repository.network.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val artistRepository: ArtistRepository,
) : ViewModel() {

    val records: MutableSharedFlow<List<Recording>> = MutableStateFlow(emptyList())

    fun getRecords(artistId: String) {
        viewModelScope.launch {
            records.emit(artistRepository.getRecordings(artistId = artistId))
        }
    }
}
