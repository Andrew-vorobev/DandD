package com.example.dandd.presentation.ui.fragment.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandd.domain.converter.ItemToItemView
import com.example.dandd.domain.usecase.ItemsUseCase
import com.example.dandd.presentation.ui.model.item.ItemView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DataDetailViewModel(
    private val useCase: ItemsUseCase,
    private val itemToItemView: ItemToItemView
) : ViewModel() {
    private var _item = MutableStateFlow<ItemView?>(null)
    val item: StateFlow<ItemView?> = _item

    private fun loading(index: String){
        viewModelScope.launch {
            _item.value = itemToItemView.convert(useCase.getItem(index = index))
        }
    }

    fun load(index: String){
        loading(index)
    }
}