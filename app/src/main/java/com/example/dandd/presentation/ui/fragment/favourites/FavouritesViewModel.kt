package com.example.dandd.presentation.ui.fragment.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandd.domain.converter.ClassToClassView
import com.example.dandd.domain.usecase.ClassUseCase
import com.example.dandd.presentation.ui.model.ClassView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val useCase: ClassUseCase,
    private val converter: ClassToClassView
) : ViewModel() {
    private val _items = MutableStateFlow<List<ClassView>>(emptyList())
    val items: StateFlow<List<ClassView>> = _items.asStateFlow()

    init {
        viewModelScope.launch {
            _items.value = useCase.getClassesDatabase().map { converter.convertToView(it) }
        }
    }
}