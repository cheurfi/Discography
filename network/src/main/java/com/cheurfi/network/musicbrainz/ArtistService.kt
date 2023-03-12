package com.cheurfi.network.musicbrainz

import com.cheurfi.network.musicbrainz.data.Artists
import retrofit2.http.*

interface ArtistService {

    @Headers("Accept: application/json")
    @GET("/ws/2/artist/")
    suspend fun getArtist(@Query("query") artist: String): Artists

}
