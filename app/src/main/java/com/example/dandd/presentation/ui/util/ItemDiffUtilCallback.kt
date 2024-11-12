package com.example.dandd.presentation.ui.util

import androidx.recyclerview.widget.DiffUtil
import com.example.dandd.presentation.ui.model.ClassView

/**
 * @author Andrew
 */
class ItemDiffUtilCallback: DiffUtil.ItemCallback<ClassView>() {
    override fun areItemsTheSame(oldItem: ClassView, newItem: ClassView): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ClassView, newItem: ClassView): Boolean {
        return oldItem.name == newItem.name && oldItem.index == newItem.index
    }
}