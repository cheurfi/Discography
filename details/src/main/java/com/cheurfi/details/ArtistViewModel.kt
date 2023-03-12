package com.cheurfi.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheurfi.details.network.RecordResponse
import com.cheurfi.details.network.RecordService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val recordService: RecordService,
//    private val interactor: NetworkInteractor,
//    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    val records: MutableSharedFlow<List<RecordResponse.Recording>> = MutableStateFlow(emptyList())

    fun getRecords(artistId: String) {
        viewModelScope.launch {
            records.emit(recordService.getRecordings(artistId = artistId).recordings)
        }
    }
}

//
//    val artists = MutableStateFlow<List<Artist>>(emptyList())
//
//    fun getArtists(name: String) {
//        viewModelScope.launch(dispatcherProvider.computation()) {
//            artists.value = interactor.getArtists(name)?.artists ?: emptyList()
//        }
//    }
//}