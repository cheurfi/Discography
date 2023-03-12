package com.cheurfi.network.musicbrainz

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecordService {

    @Headers("Accept: application/json")
    @GET("/ws/2/recording")
    suspend fun getRecordings(@Query("artist") artistId: String): RecordResponse

}

data class RecordResponse(
    @SerializedName("recording-count")
    val recordingCount: Int,
    @SerializedName("recording-offset")
    val recordingOffset: Int,
    val recordings: List<Recording>,
) {

    data class Recording(
        val disambiguation: String,
        @SerializedName("first-release-date")
        val firstReleaseDate: String?,
        val id: String,
        val length: Int,
        val title: String,
        val video: Boolean,
    )
}