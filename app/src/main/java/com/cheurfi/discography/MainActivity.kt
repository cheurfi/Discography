package com.cheurfi.discography

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import di.MyApplication
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewmodel: DiscographyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            DiscographyTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    var text by remember { mutableStateOf("") }
                    val artists by viewmodel.artists.collectAsState(emptyList())
                    SearchScreen(text, onSearch = { viewmodel.getArtists(text) }, onValueChange = { text = it }, artists = artists)
                }
            }
        }
    }
}

@Composable
fun SearchScreen(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    artists: List<Artist>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Search(text, onValueChange, onSearch)
        ArtistList(artists)
    }
}

@Composable
fun Search(text: String, onValueChange: (String) -> Unit, onSearch: () -> Unit) {
    TextField(
        value = text,
        label = { Text("Find artists") },
        onValueChange = onValueChange,
        modifier = Modifier
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
fun ArtistList(artists: List<Artist>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.padding(20.dp)) {
        items(artists) { artist ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
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
                text = "Pharrel",
                onValueChange = {},
                onSearch = {},
                artists = listOf(),
            )
        }
    }
}