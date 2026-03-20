package com.example.myapplication.applist

import androidx.lifecycle.ViewModel
import com.example.myapplication.FakeAppData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class AppsListViewModel : ViewModel() {

    private val _state = MutableStateFlow(AppsListState())
    val state: StateFlow<AppsListState> = _state

    private val _events = Channel<AppsListEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        _state.update { it.copy(items = FakeAppData.apps, isLoading = false) }
    }

    fun onLogoClick(message: String) {
        _events.trySend(AppsListEvent.ShowSnackbar(message))
    }
}