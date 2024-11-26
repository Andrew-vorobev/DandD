package com.example.dandd.presentation.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandd.domain.usecase.DatastoreUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @author Andrew
 */
class MainActivityViewModel(private val datastoreUseCase: DatastoreUseCase): ViewModel() {
    private val _sorted: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val sorted: StateFlow<Boolean> = _sorted.asStateFlow()

    init {
        viewModelScope.launch {
            datastoreUseCase.get().collect{
                _sorted.value = it
            }
        }
    }
}