package com.sopt.dive.data.type

sealed class HomeListType{
    enum class Birth {
        NONE, BIRTH
    }

    enum class Content {
        NONE, Exist
    }

    sealed interface Etc {
        data object None : Etc
        data class Music(val music: String) : Etc
        data object Gift : Etc
    }
}