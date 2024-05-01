//package me.algosketch.nestednavigationsample
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.channels.Channel
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.receiveAsFlow
//import kotlinx.coroutines.launch
//
//sealed interface DetailEvent {
//    data object NavigateUp : DetailEvent
//    data object NavigateToDescription : DetailEvent
//}
//
//class DetailViewModel : ViewModel() {
//    private val _events = Channel<DetailEvent>()
//    val events: Flow<DetailEvent> = _events.receiveAsFlow()
//
//    fun sendEvent(event: DetailEvent) = viewModelScope.launch {
//        _events.send(event)
//    }
//
//    fun preventEvents() {
//        _events.cancel()
//    }
//}
