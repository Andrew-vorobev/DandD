package com.example.dungeonanddragonsapp.presentation.ui.fragment.detail

import androidx.lifecycle.ViewModel
import com.example.dandd.presentation.ui.model.item.ItemView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataDetailViewModel : ViewModel() {
    private var _item = MutableStateFlow<ItemView?>(null)
    val item: StateFlow<ItemView?> = _item

    fun getIndex(){}
}