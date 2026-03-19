package com.example.myapplication.main.presentation.appdetails

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class AppDetailsViewModel : ViewModel() {

    private val _state = MutableStateFlow(AppDetailsState())
    val state: StateFlow<AppDetailsState> = _state

    private val _events = Channel<AppDetailsEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        val app = FakeAppDetailsData.getAppDetails(id = "sber_online")
        _state.update { it.copy(isLoading = false, app = app) }
    }

    fun onReadMoreClick() {
        _state.update { it.copy(isDescriptionExpanded = true) }
    }

    fun onLogoClick(message: String) {
        _events.trySend(AppDetailsEvent.ShowSnackbar(message))
    }
}