package com.example.dandd.presentation.ui.fragment.dataList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandd.domain.converter.ItemToItemView
import com.example.dandd.domain.usecase.ItemsUseCase
import com.example.dungeonanddragonsapp.presentation.ui.model.item.ItemView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DataListViewModel(
    private val useCase: ItemsUseCase,
    private val converter: ItemToItemView
) : ViewModel() {

    private val _items = MutableStateFlow<List<ItemView>>(emptyList())
    val items: StateFlow<List<ItemView>> = _items.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems(){
        viewModelScope.launch {
            _items.value = useCase.getItems().first().map { converter.convert(it) }
        }
    }
}