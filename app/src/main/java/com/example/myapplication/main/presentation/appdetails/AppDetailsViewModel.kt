package com.example.myapplication.main.presentation.appdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.main.domain.GetAppDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AppDetailsState())
    val state: StateFlow<AppDetailsState> = _state

    private val _events = Channel<AppDetailsEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        val appId: String = checkNotNull(savedStateHandle["id"]) {
            "ID приложения не найден"
        }

        viewModelScope.launch {
            try {
                val app = getAppDetailsUseCase(appId)

                _state.update { it.copy(isLoading = false, app = app) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, app = null) }
                _events.trySend(AppDetailsEvent.ShowSnackbar(R.string.loading_error))
            }
        }
    }

    fun onReadMoreClick() {
        _state.update { it.copy(isDescriptionExpanded = true) }
    }

    fun onLogoClick() {
        _events.trySend(AppDetailsEvent.ShowSnackbar(R.string.under_developement))
    }
}