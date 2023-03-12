package com.cheurfi.details.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

const val ARTIST_DETAILS = "ARTIST_DETAILS"
const val ARGUMENT_ARTIST = "artist"

@Composable
fun ArtistScreen(artistId: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(modifier = Modifier.padding(5.dp), text = artistId)
//        Text(modifier = Modifier.padding(5.dp), text = artist.country ?: "")
//        Text(modifier = Modifier.padding(5.dp), text = artist.type ?: "")
//        Text(modifier = Modifier.padding(5.dp), text = artist.disambiguation ?: "")
    }
}
