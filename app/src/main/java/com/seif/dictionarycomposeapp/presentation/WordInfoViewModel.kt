package com.seif.dictionarycomposeapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seif.dictionarycomposeapp.common.Resource
import com.seif.dictionarycomposeapp.domain.usecases.FetchWordInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val fetchWordInfoUseCase: FetchWordInfoUseCase
) : ViewModel() {

    private var _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private var _state: MutableState<WordInfoState> = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    // the event flow will be used to send one time events to the ui like showing a snake bar for an error message
    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query // to update that in our ui
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500L)
            fetchWordInfoUseCase(query).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "unknown error"))
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}