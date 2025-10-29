package com.sopt.dive.data

data class Home(
    val img: Int,
    val name: String,
    val message: String,
    val birth: Birth,
    val content: Content,
    val etc: Etc
)

enum class Birth {
    NONE, BIRTH
}

enum class Content {
    NONE, Exist
}

sealed class Etc {
    data object None: Etc()
    data class Music(val music: String) : Etc()
    data object Gift: Etc()
}