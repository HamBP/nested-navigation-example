package me.algosketch.nestednavigationsample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
