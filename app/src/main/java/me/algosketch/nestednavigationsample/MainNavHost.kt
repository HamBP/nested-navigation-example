package me.algosketch.nestednavigationsample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MainNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable(route = "home") {
            HomeScreen(
                navigateToDetail = { navController.navigate("detail/$it") }
            )
        }

        navigation(
            route = "detail/{id}",
            startDestination = "summary",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            composable(route = "summary") { entry ->
                val parentEntry = remember(entry) { navController.getBackStackEntry("detail/{id}") }
                val id = parentEntry.arguments!!.getInt("id")
                // startDestination으로만 진입할 수 있다면 아래 코드도 가능하다
                // val id = entry.arguments!!.getInt("id")
                DetailScreen(
                    id = id,
                    navigateToDescription = { navController.navigate("description") },
                    popBackstack = navController::popBackStack,
                )
            }

            composable(route = "description") { entry ->
                val parentEntry = remember(entry) { navController.getBackStackEntry("detail/{id}") }
                val id = parentEntry.arguments!!.getInt("id")
                DetailContentScreen(id)
            }
        }
    }
}
