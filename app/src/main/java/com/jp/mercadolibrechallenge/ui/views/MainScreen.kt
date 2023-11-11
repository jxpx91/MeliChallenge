package com.jp.mercadolibrechallenge.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jp.mercadolibrechallenge.ui.views.detailsView.DetailsView
import com.jp.mercadolibrechallenge.ui.views.resultListView.ResultListView
import com.jp.mercadolibrechallenge.ui.views.searchView.SearchView

class Screens() {
    val SEARCH = "search"
    val RESULT_LIST = "list/"
    val RESULT_LIST_QUERY = "{query}"
    val RESULT_LIST_PATH = RESULT_LIST + RESULT_LIST_QUERY
    val DETAILS = "details"
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens().SEARCH,
        modifier = Modifier,
    ) {
        composable(route = Screens().SEARCH) {
            SearchView(
                goToResultList = { query ->
                    navController.navigate(Screens().RESULT_LIST + query)
                }
            )
        }
        composable(
            route = Screens().RESULT_LIST_PATH,
            arguments = listOf(navArgument("query") { type = NavType.StringType })
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query").orEmpty()
            ResultListView(
                query = query,
                goToDetails = {
                    navController.navigate(Screens().DETAILS)
                },
                navigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = Screens().DETAILS
        ) {
            DetailsView(
                navigateUp = { navController.navigateUp() }
            )
        }
    }
}