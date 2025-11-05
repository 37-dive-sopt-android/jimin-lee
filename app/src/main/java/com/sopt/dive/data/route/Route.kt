package com.sopt.dive.data.route

import kotlinx.serialization.Serializable

interface Route

@Serializable
data object Login : Route

@Serializable
data object SignUp : Route