package com.cheurfi.repository.network

import android.util.Log
import com.cheurfi.repository.data.Artists
import com.cheurfi.repository.data.Recording
import com.cheurfi.utils.coroutines.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicBrainzArtistRepository @Inject constructor(
    private val artistService: ArtistService,
    private val recordService: RecordService,
    private val dispatcher: DispatcherProvider,
) : ArtistRepository {

    override suspend fun getArtists(artist: String): Artists? =
        withContext(dispatcher.computation()) {
            try {
                artistService.getArtist(artist)
            } catch (t: Throwable) {
                Log.e("Server error", "MusicBrainz server error: $t")
                null
            }
        }

    override suspend fun getRecordings(artistId: String): List<Recording> {
        return withContext(dispatcher.computation()) {
            try {
                recordService.getRecordings(artistId)
                    .recordings.map {
                        Recording(
                            title = it.title,
                            firstReleaseDate = it.firstReleaseDate,
                        )
                    }
            } catch (t: Throwable) {
                Log.e("Server error", "MusicBrainz server error: $t")
                emptyList()
            }
        }
    }
}