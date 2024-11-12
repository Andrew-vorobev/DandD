package com.example.dandd.presentation.ui.fragment.notifications

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
    private val classUseCase: ClassUseCase,
    private val classConverter: ClassToClassView
) : ViewModel() {
    private val _items = MutableStateFlow<List<ClassView>>(emptyList())
    val items: StateFlow<List<ClassView>> = _items.asStateFlow()

    init {
        loadItems()
    }

    private fun loadItems(){
        viewModelScope.launch {
            _items.value = classUseCase.getClassesDatabase().map { classConverter.convertToView(it) }
        }
    }

    fun delete(classView: ClassView){
        viewModelScope.launch {
            classUseCase.delete(classItem = classConverter.convertToItem(classView))
        }
    }
}