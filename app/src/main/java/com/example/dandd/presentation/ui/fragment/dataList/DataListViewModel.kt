package com.example.dandd.presentation.ui.fragment.dataList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandd.domain.converter.ClassToClassView
import com.example.dandd.domain.usecase.ClassUseCase
import com.example.dandd.domain.usecase.DatastoreUseCase
import com.example.dandd.presentation.ui.model.ClassView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DataListViewModel(
    private val classUseCase: ClassUseCase,
    private val datastoreUseCase: DatastoreUseCase,
    private val classConverter: ClassToClassView
) : ViewModel() {

    private val _items = MutableStateFlow<List<ClassView>>(emptyList())
    val items: StateFlow<List<ClassView>> = _items.asStateFlow()

    private val _sorted: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val sorted: StateFlow<Boolean> = _sorted.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            _items.value = classUseCase.getClasses().map { classConverter.convertToView(it) }
            _sorted.value = datastoreUseCase.get().first()
            sort()
        }
    }

    fun save(classView: ClassView) {
        viewModelScope.launch {
            classUseCase.save(classItem = classConverter.convertToItem(classView))
        }
    }

    private fun sort() {
        if (_sorted.value) {
            _items.value = _items.value.sortedBy { it.name }.reversed()
        } else {
            _items.value = _items.value.sortedBy { it.name }
        }
    }

    fun sorting() {
        viewModelScope.launch {
            _sorted.value = !_sorted.value
            datastoreUseCase.save(_sorted.value)
            sort()
        }
    }
}