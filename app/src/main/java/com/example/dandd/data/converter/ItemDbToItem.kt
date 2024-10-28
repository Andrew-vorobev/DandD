package com.example.dungeonanddragonsapp.data.converter

import com.example.dungeonanddragonsapp.data.model.Item
import com.example.dungeonanddragonsapp.data.model.ItemDb

/**
 * @author Andrew
 */
interface ItemDbToItem {
    fun convert(itemDb: ItemDb): Item
}

class ItemDbToItemImpl() : ItemDbToItem {
    override fun convert(itemDb: ItemDb): Item {
        return Item(
            index = itemDb.index,
            name = itemDb.name,
            fullName = itemDb.fullName,
            desc = itemDb.desc,
            skills = itemDb.skills,
            url = itemDb.url
        )
    }
}