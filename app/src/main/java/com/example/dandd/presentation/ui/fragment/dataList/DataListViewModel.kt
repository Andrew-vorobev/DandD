package com.example.dungeonanddragonsapp.presentation.ui.fragment.dataList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dungeonanddragonsapp.data.converter.ItemDbToItemViewImpl
import com.example.dungeonanddragonsapp.data.dao.ItemDaoImpl
import com.example.dungeonanddragonsapp.data.repo.ItemRepositoryImpl
import com.example.dungeonanddragonsapp.presentation.ui.model.item.ItemView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataListViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<ItemView>>(emptyList())
    val items: StateFlow<List<ItemView>> = _items.asStateFlow()

    private val repo = ItemRepositoryImpl(
        itemDao = ItemDaoImpl(),
        itemDbToItemView = ItemDbToItemViewImpl()
    )

    init {
        viewModelScope.launch {
            _items.value = repo.getItems()
        }
    }
}