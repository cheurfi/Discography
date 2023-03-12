package com.cheurfi.search.ui

import com.cheurfi.search.data.Artist

sealed class UiState

data class NetworkingError(
    val displayMessage: String?,
) : UiState()

data class Success(
    val artists: List<Artist>,
) : UiState()

object Loading : UiState()
