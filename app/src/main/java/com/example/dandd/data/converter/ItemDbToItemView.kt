package com.example.dungeonanddragonsapp.data.converter

import com.example.dungeonanddragonsapp.data.model.ItemDb
import com.example.dungeonanddragonsapp.presentation.ui.model.item.ItemView

/**
 * @author Andrew
 */
interface ItemDbToItemView {
    fun convert(itemDb: ItemDb): ItemView
}

class ItemDbToItemViewImpl() : ItemDbToItemView {
    override fun convert(itemDb: ItemDb): ItemView {
        return ItemView(
            index = itemDb.index,
            name = itemDb.name,
            fullName = itemDb.fullName,
            desc = itemDb.desc,
            skills = itemDb.skills,
            url = itemDb.url
        )
    }
}