package com.example.dungeonanddragonsapp.presentation.ui.util

import androidx.recyclerview.widget.DiffUtil
import com.example.dungeonanddragonsapp.presentation.ui.model.item.ItemView

/**
 * @author Andrew
 */
class ItemDiffUtilCallback: DiffUtil.ItemCallback<ItemView>() {
    override fun areItemsTheSame(oldItem: ItemView, newItem: ItemView): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItemView, newItem: ItemView): Boolean {
        return oldItem.fullName == newItem.fullName && oldItem.skills == newItem.skills
    }
}