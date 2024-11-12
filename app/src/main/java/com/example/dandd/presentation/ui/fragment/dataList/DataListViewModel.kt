package com.example.dandd.presentation.ui.fragment.dataList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandd.domain.converter.ClassToClassView
import com.example.dandd.domain.usecase.ClassUseCase
import com.example.dandd.presentation.ui.model.ClassView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataListViewModel(
    private val classUseCase: ClassUseCase,
    private val classConverter: ClassToClassView
) : ViewModel() {

    private val _items = MutableStateFlow<List<ClassView>>(emptyList())
    val items: StateFlow<List<ClassView>> = _items.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems(){
        viewModelScope.launch {
            _items.value = classUseCase.getClasses().map { classConverter.convert(it) }
        }
    }
}