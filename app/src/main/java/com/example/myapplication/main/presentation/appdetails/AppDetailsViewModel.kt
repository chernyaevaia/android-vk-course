package com.example.myapplication.main.presentation.appdetails

import AppDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.main.data.AppDetailsApi
import com.example.myapplication.main.data.AppDetailsRepositoryImpl
import com.example.myapplication.main.domain.GetAppDetailsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppDetailsViewModel : ViewModel() {

    private val _state = MutableStateFlow(AppDetailsState())
    val state: StateFlow<AppDetailsState> = _state

    private val _events = Channel<AppDetailsEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private val useCase = GetAppDetailsUseCase(
        appDetailsRepository = AppDetailsRepositoryImpl(
            appApi = AppDetailsApi(),
            mapper = AppDetailsMapper(),
        )
    )

    init {
        viewModelScope.launch {
            try {
                val app = useCase()
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