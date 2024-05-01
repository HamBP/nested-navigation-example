package me.algosketch.nestednavigationsample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(
    id: Int,
    navigateToDescription: () -> Unit,
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = navigateToDescription),
            text = "id ${id}에 대한 상세 : 상세 화면에서는 이렇게 내용이 적... 더보기",
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Gray)
        ) {
            Text("장식용 이미지")
        }
    }
}
