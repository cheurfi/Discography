package network

import com.cheurfi.discography.data.Artists
import retrofit2.http.*

interface MusicBrainzService {

    @Headers("Content-Type: application/json")
    @GET("/artist")
    suspend fun getArtist(@Query("query") artist: String): Artists

}
