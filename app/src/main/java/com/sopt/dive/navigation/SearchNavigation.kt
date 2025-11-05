package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.dive.data.route.Search
import com.sopt.dive.ui.screen.SearchScreen

fun NavGraphBuilder.searchNavGraph(
    innerPadding: PaddingValues
) {
    composable<Search>{
        SearchScreen(innerPadding)
    }
}