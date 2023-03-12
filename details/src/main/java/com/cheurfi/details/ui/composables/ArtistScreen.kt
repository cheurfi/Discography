package com.cheurfi.details.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cheurfi.details.ArtistViewModel

const val ARTIST_DETAILS = "ARTIST_DETAILS"
const val ARGUMENT_ARTIST = "artist"

@Composable
fun ArtistScreen(artistId: String = "f835c755-d70b-42d8-a979-01f67c3c47e8") {

    val viewModel: ArtistViewModel = hiltViewModel()

    val records by viewModel.records.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getRecords(artistId = artistId)
    })

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        items(records) { record ->
//            Text(modifier = Modifier.padding(5.dp), text = artistId)
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    ) {
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = record.title,
                            style = MaterialTheme.typography.labelLarge,
                        )
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = record.firstReleaseDate.orEmpty(),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
            }
        }
    }
}
