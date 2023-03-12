package com.cheurfi.search.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cheurfi.search.SearchViewModel
import com.cheurfi.search.data.Artist
import com.cheurfi.search.ui.theme.DiscographyTheme

const val SEARCH_SCREEN = "SEARCH_SCREEN"

@Composable
fun SearchScreen(
    onArtistSelected: (String) -> Unit,
) {
    val viewModel: SearchViewModel = hiltViewModel()
    var text by remember { viewModel.searchText }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val artists by viewModel.artists.collectAsState(emptyList())
        Search(
            text = text,
            onValueChange = { text = it },
            onSearch = { viewModel.getArtists(text) }
        )
        AnimatedVisibility(visible = artists.isNotEmpty()) {
            ArtistList(
                artists = artists,
                onArtistSelected = onArtistSelected
            )
        }
    }
}

@Composable
fun Search(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
) {
    TextField(
        value = text,
        label = { Text("Find artists") },
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch()
        }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}

@Composable
fun ArtistList(
    modifier: Modifier = Modifier,
    artists: List<Artist>,
    onArtistSelected: (String) -> Unit,
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier.padding(20.dp)) {
        items(artists) { artist ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onArtistSelected(artist.id) }
            ) {
                Text(artist.name, modifier = Modifier.padding(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiscographyTheme {
        Surface {
            SearchScreen(
                onArtistSelected = {},
            )
        }
    }
}