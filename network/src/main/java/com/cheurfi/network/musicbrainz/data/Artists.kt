package com.cheurfi.network.musicbrainz.data

data class Artists(
    val artists: List<Artist>,
    val count: Int,
    val created: String,
    val offset: Int
)