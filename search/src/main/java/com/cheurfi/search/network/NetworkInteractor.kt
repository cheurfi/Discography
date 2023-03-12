package com.cheurfi.search.network

import android.util.Log
import com.cheurfi.search.data.Artists
import com.cheurfi.utils.coroutines.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkInteractor @Inject constructor(
    private val service: MusicBrainzService,
    private val dispatcher: DispatcherProvider,
) {

    suspend fun getArtists(artist: String): Artists? =
        withContext(dispatcher.computation()) {
            try {
                service.getArtist(artist)
            } catch (t: Throwable) {
                Log.e("Server error", "MusicBrainz server error: $t")
                null
            }
        }
}