package com.example.dungeonanddragonsapp.data.converter

import android.net.Uri
import com.example.dandd.data.model.ItemDb
import com.example.dungeonanddragonsapp.data.model.Item

/**
 * @author Andrew
 */
interface ItemDbToItem {
    fun convert(itemDb: ItemDb): Item
}

class ItemDbToItemImpl() : ItemDbToItem {
    override fun convert(itemDb: ItemDb): Item {
        return Item(
            index = itemDb.index ?: "barbarian",
            name = itemDb.name ?: "",
            fullName = itemDb.fullName ?: "",
            desc = itemDb.desc ?: emptyList(),
            skills = itemDb.skills ?: emptyList(),
            url = itemDb.url ?: Uri.parse("")
        )
    }
}