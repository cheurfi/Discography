package com.cheurfi.repository.network

import com.cheurfi.repository.data.Artist
import com.cheurfi.repository.data.Recording

interface ArtistRepository {
    suspend fun getArtists(artist: String): List<Artist>
    suspend fun getRecordings(artistId: String): List<Recording>
}