package com.sopt.dive.data

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>

    data class Failure(
        val message: String? = "",
        val throwable: Throwable? = null
    ) : UiState<Nothing>

    object Loading : UiState<Nothing>
}