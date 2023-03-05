package com.cheurfi.discography.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cheurfi.discography.data.Artist
import com.cheurfi.discography.ui.theme.DiscographyTheme

@Composable
fun SearchScreen(
    onSearch: (String) -> Unit,
    artists: List<Artist>,
    onArtistSelected: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Search(
            text = text,
            onValueChange = { text = it },
            onSearch = { onSearch(text) }
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

@OptIn(ExperimentalMaterial3Api::class)
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
                onSearch = {},
                artists = listOf(),
                onArtistSelected = {},
            )
        }
    }
}