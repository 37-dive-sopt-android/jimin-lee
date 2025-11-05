package com.sopt.dive.data.route

import kotlinx.serialization.Serializable

interface MainTabRoute : Route

@Serializable
data object Home : MainTabRoute

@Serializable
data object Search : MainTabRoute

@Serializable
data object My : MainTabRoute