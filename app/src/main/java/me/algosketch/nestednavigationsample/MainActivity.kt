package me.algosketch.nestednavigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.algosketch.nestednavigationsample.ui.theme.NestedNavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedNavigationSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavHost()
                }
            }
        }
    }
}

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
                val id = entry.arguments!!.getInt("id")
                DetailScreen(
                    id = id,
                    navigateToDescription = { navController.navigate("description/${it}") }
                )
            }

            composable(
                route = "description/{desId}",
                arguments = listOf(
                    navArgument("desId") { type = NavType.IntType }
                )
            ) { entry ->
                val id = entry.arguments!!.getInt("desId")
                DetailContentScreen(id)
            }
        }
    }
}

@Composable
fun HomeScreen(
    navigateToDetail: (Int) -> Unit,
) {
    val contents = (1..100).toList()

    LazyColumn {
        items(contents) { id ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable { navigateToDetail(id) },
                text = "상세 보기 $id",
            )
        }
    }
}

@Composable
fun DetailScreen(
    id: Int,
    navigateToDescription: (Int) -> Unit,
) {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .clickable { navigateToDescription(id) },
        text = "id ${id}에 대한 상세 : 상세 화면에서는 이렇게 내용이 적혀 있는데, 만약 내용이 길어지면 내용 클릭을 통해 ...더보기",
    )
}

@Composable
fun DetailContentScreen(id: Int) {
    Text("id ${id}에 대한 상세 : 상세 화면에서는 이렇게 내용이 적혀 있는데, 만약 내용이 길어지면 내용 클릭을 통해 전체 내용을 확인할 수 있다.")
}
