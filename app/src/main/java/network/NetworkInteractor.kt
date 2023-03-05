package network

import coroutines.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkInteractor @Inject constructor(
    private val service: MusicBrainzService,
    private val dispatcher: DispatcherProvider,
) {

    suspend fun getArtists(artist: String) =
        withContext(dispatcher.computation()) {
            service.getArtist(artist)
        }
}
