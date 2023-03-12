package com.cheurfi.discography.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainLayout() {
    val navController = rememberNavController()
    Surface {
        NavigationGraph(navController = navController)
    }
}