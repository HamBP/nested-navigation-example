package me.algosketch.nestednavigationsample

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreen(
    id: Int,
    navigateToDescription: () -> Unit,
    popBackstack: () -> Unit,
    viewModel: DetailViewModel = viewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                DetailEvent.NavigateUp -> {
                    viewModel.preventEvents()
                    popBackstack()
                }

                DetailEvent.NavigateToDescription -> navigateToDescription()
            }
        }
    }

    BackHandler {
        // 여기서 prevent를 호출할 수도 있지만, 일관된 처리를 위해 이벤트를 경유한다.
        // 실제로는 시스템 백버튼 뿐만 아니라 뒤로가기 버튼도 처리할 가능성이 높다.
        viewModel.sendEvent(DetailEvent.NavigateUp)
    }

    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.sendEvent(DetailEvent.NavigateToDescription) },
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
