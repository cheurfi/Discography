package com.cheurfi.discography.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cheurfi.details.ui.composables.ARGUMENT_ARTIST
import com.cheurfi.details.ui.composables.ARTIST_DETAILS
import com.cheurfi.details.ui.composables.ArtistScreen
import com.cheurfi.details.ui.theme.DiscographyTheme
import com.cheurfi.search.ui.composables.SEARCH_SCREEN
import com.cheurfi.search.ui.composables.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    DiscographyTheme {
        NavHost(navController = navController, startDestination = SEARCH_SCREEN) {
            composable(SEARCH_SCREEN) { SearchScreen { artist -> navController.navigate("$ARTIST_DETAILS/$artist") } }
            composable(
                "$ARTIST_DETAILS/{$ARGUMENT_ARTIST}",
                arguments = listOf(navArgument(ARGUMENT_ARTIST) { type = NavType.StringType })
            ) {
                ArtistScreen(it.arguments?.getString(ARGUMENT_ARTIST) ?: "")
            }
        }
    }
}