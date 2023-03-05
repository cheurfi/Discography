package com.cheurfi.discography

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.cheurfi.discography.ui.composables.ArtistScreen
import com.cheurfi.discography.ui.composables.SearchScreen
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
            var selectedArtist by remember { mutableStateOf<String?>(null) }

            if (selectedArtist == null) {
                SearchPage(viewmodel = viewmodel, onArtistSelected = { selectedArtist = it })
            } else {
                val artist =
                ArtistPage(viewmodel = viewmodel, id = selectedArtist!!)
                BackHandler {
                    selectedArtist = null
                }
            }
        }
    }

}

@Composable
private fun SearchPage(viewmodel: DiscographyViewModel, onArtistSelected: (String) -> Unit) {
    DiscographyTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

            val artists by viewmodel.artists.collectAsState(emptyList())
            SearchScreen(
                onSearch = { viewmodel.getArtists(it) },
                artists = artists,
                onArtistSelected = onArtistSelected
            )
        }
    }
}

@Composable
private fun ArtistPage(viewmodel: DiscographyViewModel, id: String) {
    DiscographyTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

            val artists by viewmodel.artists.collectAsState()
            val artist = artists.first { id == it.id }
            ArtistScreen(artist = artist)
        }
    }
}