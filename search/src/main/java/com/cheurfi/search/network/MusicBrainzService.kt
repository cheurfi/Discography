package com.cheurfi.search.network

import com.cheurfi.search.data.Artists
import retrofit2.http.*

interface MusicBrainzService {

    @Headers("Accept: application/json")
    @GET("/ws/2/artist/")
    suspend fun getArtist(@Query("query") artist: String): Artists

}
