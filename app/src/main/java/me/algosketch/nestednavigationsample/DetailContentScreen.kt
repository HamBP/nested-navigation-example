package me.algosketch.nestednavigationsample

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailContentScreen(id: Int) {
    Text("id ${id}에 대한 상세 : 상세 화면에서는 이렇게 내용이 적혀 있는데, 만약 내용이 길어지면 내용 클릭을 통해 전체 내용을 확인할 수 있다.")
}
