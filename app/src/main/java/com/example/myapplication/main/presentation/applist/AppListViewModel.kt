package com.example.myapplication.main.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.main.domain.GetAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppsListViewModel @Inject constructor(
    private val useCase: GetAppsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AppsListState())
    val state: StateFlow<AppsListState> = _state

    private val _events = Channel<AppsListEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            val items = useCase()
            _state.update { it.copy(items = items, isLoading = false) }
        }
    }

    fun onLogoClick() {
        _events.trySend(AppsListEvent.ShowSnackbar(R.string.app_name))
    }
}