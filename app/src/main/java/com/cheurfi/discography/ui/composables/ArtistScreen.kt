package com.cheurfi.discography.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cheurfi.discography.data.Artist

@Composable
fun ArtistScreen(artist: Artist) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(modifier = Modifier.padding(5.dp), text = artist.name)
        Text(modifier = Modifier.padding(5.dp), text = artist.country ?: "")
        Text(modifier = Modifier.padding(5.dp), text = artist.type ?: "")
        Text(modifier = Modifier.padding(5.dp), text = artist.disambiguation ?: "")
    }
}
