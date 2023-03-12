package com.cheurfi.repository.network

import com.cheurfi.repository.data.Artists
import com.cheurfi.repository.data.Recording

interface ArtistRepository {
    suspend fun getArtists(artist: String): Artists?
    suspend fun getRecordings(artistId: String): List<Recording>
}